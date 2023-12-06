package com.chrrissoft.alarmmanager.entities

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.PendingIntentCompat
import com.chrrissoft.alarmmanager.MainActivity
import com.chrrissoft.alarmmanager.MainService
import com.chrrissoft.alarmmanager.app.OnAlarmReceiver
import com.chrrissoft.alarmmanager.utils.Util.ui

enum class AlarmOperation {
    Local, Activity, Service, Receiver,
    ;

    val label = name.ui

    companion object {
        val operations = listOf(Local, Activity, Service, Receiver)
        val repeatingOperations = listOf(Activity, Service, Receiver)
        val AlarmOperation.component
            get() = run {
                when (this) {
                    Local -> null
                    Activity -> MainActivity::class.java
                    Service -> MainService::class.java
                    Receiver -> OnAlarmReceiver::class.java
                }
            }

        fun AlarmOperation.getIntent(ctx: Context): Intent? {
            return component?.let { Intent(ctx, it) }
        }

        fun AlarmOperation.getPendingIntent(ctx: Context): PendingIntent? {
            return getIntent(ctx)?.let {
                when (this) {
                    Local -> null
                    Activity -> PendingIntentCompat.getActivity(ctx, (0), it, (0), (false))
                    Service -> PendingIntentCompat.getActivity(ctx, (0), it, (0), (false))
                    Receiver -> PendingIntentCompat.getActivity(ctx, (0), it, (0), (false))
                }
            }
        }
    }
}
