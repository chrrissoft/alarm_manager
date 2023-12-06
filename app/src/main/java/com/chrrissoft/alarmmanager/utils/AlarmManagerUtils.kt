package com.chrrissoft.alarmmanager.utils

import android.app.AlarmManager
import android.app.AlarmManager.AlarmClockInfo
import android.content.Context
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.N
import android.os.Build.VERSION_CODES.S
import android.os.Handler
import androidx.core.app.NotificationManagerCompat
import com.chrrissoft.alarmmanager.base.Alarm
import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.entities.AlarmOperation.Activity
import com.chrrissoft.alarmmanager.entities.AlarmOperation.Companion.getPendingIntent
import com.chrrissoft.alarmmanager.entities.AlarmOperation.Local
import com.chrrissoft.alarmmanager.entities.AlarmOperation.Receiver
import com.chrrissoft.alarmmanager.entities.AlarmOperation.Service
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import com.chrrissoft.alarmmanager.utils.AlarmUtils.getPendingIntent
import com.chrrissoft.alarmmanager.utils.NotificationManagerUtils.onAlarmNotify
import com.chrrissoft.alarmmanager.utils.Util.Try
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm
import kotlinx.serialization.json.Json

object AlarmManagerUtils {
    fun OnAlarmListener(
        alarm: Alarm,
        ctx: Context,
        notificationManager: NotificationManagerCompat,
        listeners: MutableMap<String, AlarmManager.OnAlarmListener>,
        onFinish: () -> Unit,
    ): AlarmManager.OnAlarmListener? {
        return if (alarm.operation == Local && SDK_INT >= N) {
            AlarmManager.OnAlarmListener {
                Try(ctx) {
                    notificationManager.onAlarmNotify(alarm, ctx)
                    listeners.remove(alarm.id)
                    onFinish()
                }
            }.apply { listeners[alarm.id] = this }
        } else null
    }

    fun AlarmManager.cancel(
        alarm: Alarm,
        json: Json,
        ctx: Context,
        listeners: MutableMap<String, AlarmManager.OnAlarmListener>
    ): Boolean {
        return try {
            when (alarm.operation) {
                Local ->
                    if (SDK_INT >= N) listeners.remove(alarm.id)?.let { cancel(it) }

                Activity, Service, Receiver ->
                    cancel(alarm.getPendingIntent(json = json, ctx = ctx)!!)
            }
            true
        } catch (e: Throwable) {
            e.printStackTrace()
            false
        }
    }

    fun AlarmManager.setWindow(
        ctx: Context,
        json: Json,
        alarm: WindowAlarm,
        flags: Int = 0,
        handler: Handler? = null,
        listener: AlarmManager.OnAlarmListener? = null,
    ) {
        val pendingIntent = alarm.getPendingIntent(ctx, json, flags = flags)

        if (alarm.operation == Local) {
            if (SDK_INT >= N) {
                requireNotNull(listener) { "Listener can't be null when operation is Local" }
                setWindow(
                    alarm.type.original,
                    alarm.triggerTime,
                    alarm.rawWindow,
                    alarm.tag,
                    listener,
                    handler
                )
            } else throw Throwable("Local operation do not support")
        } else {
            if (pendingIntent == null) throw Throwable("pending intent is null")
            setWindow(alarm.type.original, alarm.triggerTime, alarm.rawWindow, pendingIntent)
        }
    }

    fun AlarmManager.setRepeating(
        ctx: Context,
        json: Json,
        alarm: RepeatingAlarm,
        listener: AlarmManager.OnAlarmListener? = null,
        handler: Handler? = null,
    ) {
        alarm.apply {
            if (exact) {
                if (alarm.idle && alarm.operation == Local) throw Throwable("Idle don't support local operation")

                val pendingIntent = alarm.getPendingIntent(ctx, json)

                if (alarm.operation == Local) {
                    if (SDK_INT < N) throw Throwable("Local operation not supported your on OS version")
                    requireNotNull(listener) { "Listener can't be null when operation is Local" }
                    alarm.apply {
                        if (exact) setExact(type.original, triggerTime, tag, listener, handler)
                        else set(type.original, triggerTime, tag, listener, handler)
                    }
                } else if (alarm.idle) {
                    requireNotNull(pendingIntent) { "Pending intent is null" }
                    alarm.apply {
                        if (exact) setExactAndAllowWhileIdle(type.original, triggerTime, pendingIntent)
                        else setAndAllowWhileIdle(type.original, triggerTime, pendingIntent)
                    }
                } else {
                    requireNotNull(pendingIntent) { "Pending intent is null" }
                    alarm.apply {
                        if (exact) set(type.original, triggerTime, pendingIntent)
                        else set(type.original, triggerTime, pendingIntent)
                    }
                }
            } else {
                if (alarm.operation == Local) throw IllegalStateException("Local operation do not support")
                val pendingIntent = alarm.getPendingIntent(ctx, json, mutable = true)
                requireNotNull(pendingIntent) { "Pending intent is null" }
                setInexactRepeating(type.original, triggerTime, rawRepeating, pendingIntent)
            }
        }
    }

    fun AlarmManager.setClock(
        ctx: Context,
        json: Json,
        data: ClockAlarm,
        flags: Int = 0,
    ) {
        checkExactPermission()
        if (data.operation == Local) throw IllegalArgumentException("Local operation do not support")
        val time = data.clockInfo.triggerTime
        val pendingIntent = data.clockInfo.component.getPendingIntent(ctx)
        requireNotNull(pendingIntent) { "Pending intent is null" }
        val clock = AlarmClockInfo(time, pendingIntent)
        val operation = data.getPendingIntent(ctx, json, flags = flags)
        requireNotNull(operation) { "Pending intent is null" }
        setAlarmClock(clock, operation)
    }

    fun AlarmManager.setOneTime(
        ctx: Context,
        json: Json,
        alarm: OneTimeAlarm,
        listener: AlarmManager.OnAlarmListener? = null,
        flags: Int = 0,
        handler: Handler? = null,
    ) {
        if (alarm.idle && alarm.operation == Local) throw Throwable("Idle don't support local operation")

        val pendingIntent = alarm.getPendingIntent(ctx, json, flags = flags)

        if (alarm.operation == Local) {
            if (SDK_INT < N) throw Throwable("Local operation not supported your on OS version")
            requireNotNull(listener) { "Listener can't be null when operation is Local" }
            alarm.apply {
                if (exact) setExact(type.original, triggerTime, tag, listener, handler)
                else set(type.original, triggerTime, tag, listener, handler)
            }
        } else if (alarm.idle) {
            requireNotNull(pendingIntent) { "Pending intent is null" }
            alarm.apply {
                if (exact) setExactAndAllowWhileIdle(type.original, triggerTime, pendingIntent)
                else setAndAllowWhileIdle(type.original, triggerTime, pendingIntent)
            }
        } else {
            requireNotNull(pendingIntent) { "Pending intent is null" }
            alarm.apply {
                if (exact) set(type.original, triggerTime, pendingIntent)
                else set(type.original, triggerTime, pendingIntent)
            }
        }
    }

    private fun AlarmManager.checkExactPermission() {
        if (!exactsEnabled) throw IllegalStateException("Exacts alarm permission is disable")
    }

    private val AlarmManager.exactsEnabled
        get() = if (SDK_INT >= S) canScheduleExactAlarms() else true
}
