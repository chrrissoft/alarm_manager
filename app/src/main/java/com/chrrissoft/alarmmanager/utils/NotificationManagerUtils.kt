package com.chrrissoft.alarmmanager.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.Q
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.PendingIntentCompat.getService
import com.chrrissoft.alarmmanager.Constants
import com.chrrissoft.alarmmanager.R
import com.chrrissoft.alarmmanager.R.drawable.ic_launcher_foreground
import com.chrrissoft.alarmmanager.R.string.cancel_next_alarms
import com.chrrissoft.alarmmanager.app.CancelRepeatingAlarmService
import com.chrrissoft.alarmmanager.base.Alarm
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import com.chrrissoft.alarmmanager.utils.AlarmUtils.countText
import com.chrrissoft.alarmmanager.utils.AlarmUtils.typeText
import com.chrrissoft.alarmmanager.utils.IntentUtils.putAlarm
import com.chrrissoft.alarmmanager.utils.Util.debug
import kotlinx.serialization.json.Json
import kotlin.random.Random

@SuppressLint("MissingPermission")
object NotificationManagerUtils {
    fun NotificationManagerCompat.onAlarmNotify(
        alarm: Alarm,
        context: Context,
        id: Int = Random.nextInt(),
    ) {
        generalNotification(
            ctx = context,
            title = alarm.message,
            subText = alarm.typeText,
        ).build().let { notify(id, it) }
    }

    fun generalNotification(
        ctx: Context,
        title: String,
        subText: String? = null,
        text: String? = null,
        icon: Int = R.mipmap.ic_launcher_round,
        channel: String = Constants.NOTIFICATION_CHANNEL_ID,
    ): NotificationCompat.Builder {
//        val largeIcon = BitmapFactory.decodeResource(ctx.resources, R.mipmap.ic_launcher_round)
        debug(message = "notification - Melissa")
        return NotificationCompat.Builder(ctx, channel)
            .setContentTitle(title)
            .setOnlyAlertOnce(true)
            .setSmallIcon(icon)
//            .setLargeIcon(largeIcon)
            .setContentText(text)
            .setSubText(subText)
    }

    fun NotificationCompat.Builder.setCancelRepeatingAlarmAction(
        alarm: Alarm,
        json: Json,
        context: Context,
        icon: Int = ic_launcher_foreground,
        text: String = context.getString(cancel_next_alarms),
    ): NotificationCompat.Builder {
        if (alarm !is RepeatingAlarm) return this
        val pendingIntent = Intent(context, CancelRepeatingAlarmService::class.java)
            .apply { putAlarm(json, alarm) }
            .apply { if (SDK_INT >= Q) identifier = alarm.id }
            .let { getService(context, (0), it, (0), (true)) }
        return addAction(icon, text, pendingIntent)
    }

    fun<A : Alarm> NotificationManagerCompat.scheduledAlarmNotification(
        app: Context,
        type: String,
        res:  ResState<Map<A, ResState<*>>>,
        id: Int = Random.nextInt(),
    ) {
        if (res is ResState.Loading) return
        val notificationText = res.countText(success = "alarms scheduled of")
        val notification = generalNotification(app, notificationText, subText = type)
        notify(id, notification.build())
    }
}
