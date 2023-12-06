package com.chrrissoft.alarmmanager.utils

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.PendingIntentCompat.getActivity
import androidx.core.app.PendingIntentCompat.getBroadcast
import androidx.core.app.PendingIntentCompat.getService
import com.chrrissoft.alarmmanager.base.Alarm
import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.entities.AlarmOperation.Activity
import com.chrrissoft.alarmmanager.entities.AlarmOperation.Companion.component
import com.chrrissoft.alarmmanager.entities.AlarmOperation.Local
import com.chrrissoft.alarmmanager.entities.AlarmOperation.Receiver
import com.chrrissoft.alarmmanager.entities.AlarmOperation.Service
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import com.chrrissoft.alarmmanager.utils.IntentUtils.putAlarm
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm
import kotlinx.serialization.json.Json

object AlarmUtils {
    fun Alarm.getPendingIntent(
        ctx: Context,
        json: Json,
        code: Int = 0,
        flags: Int = 0,
        mutable: Boolean = false,
    ): PendingIntent? {
        val intent = getIntent(json, ctx) ?: return null
        return when (operation) {
            Local -> null
            Activity -> getActivity(ctx, code, intent, flags, mutable)
            Service -> getService(ctx, code, intent, flags, mutable)
            Receiver -> getBroadcast(ctx, code, intent, flags, mutable)
        }
    }

    private fun Alarm.getIntent(json: Json, context: Context): Intent? {
        return if (operation == Local) null else Intent()
            .apply { putAlarm(json = json, alarm = this@getIntent) }
            .apply { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) identifier = id }
            .apply { operation.component?.let { component = ComponentName(context, it) } }
    }

    fun<A : Alarm> ResState<Map<A, ResState<*>>>.countText(
        success: String,
        loading: String = "Loading",
        error: (Throwable?) -> String = { it?.message ?: "Unknown error" },
    ) : String {
        return when (this) {
            is ResState.Error -> error(throwable)
            ResState.Loading -> loading
            is ResState.Success -> {
                val successCount = data.count { it.value is ResState.Success }
                "$successCount $success ${data.size}"
            }
        }
    }

    val Alarm.typeText get() = run {
        when (this) {
            is OneTimeAlarm -> "One time"
            is RepeatingAlarm -> "Repeating"
            is WindowAlarm -> "Window"
            is ClockAlarm -> "Clock"
            else -> "Unknown"
        }
    }
}
