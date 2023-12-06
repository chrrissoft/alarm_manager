package com.chrrissoft.alarmmanager.app

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_BOOT_COMPLETED
import android.content.Intent.ACTION_LOCKED_BOOT_COMPLETED
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.N
import androidx.core.app.NotificationManagerCompat
import com.chrrissoft.alarmmanager.usecases.interfaces.ScheduleAlarmsOnBootUseCase
import com.chrrissoft.alarmmanager.utils.NotificationManagerUtils.generalNotification
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BootCompleteReceiver : BroadcastReceiver() {
    @Inject
    lateinit var ScheduleAlarmsOnBootUseCase: ScheduleAlarmsOnBootUseCase
    @Inject
    lateinit var notificationManagerCompat: NotificationManagerCompat

    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {
        context ?: return
        notificationManagerCompat.notify(12, generalNotification(context, "Boot").build())
        if (intent == null) return
        val boot = intent.action != ACTION_BOOT_COMPLETED
        val bootLocked = if (SDK_INT >= N) intent.action != ACTION_LOCKED_BOOT_COMPLETED else false
        if (boot || bootLocked) return
        ScheduleAlarmsOnBootUseCase()
    }
}
