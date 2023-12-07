package com.chrrissoft.alarmmanager.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_LOCKED_BOOT_COMPLETED
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.N
import com.chrrissoft.alarmmanager.usecases.interfaces.RestartScheduleAlarmsUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScheduleAlarmsOnBootLockedReceiver : BroadcastReceiver() {
    @Inject
    lateinit var RestartScheduleAlarmsUseCase: RestartScheduleAlarmsUseCase

    override fun onReceive(context: Context?, intent: Intent?) {
        intent ?: return
        if (SDK_INT < N) return
        if (intent.action != ACTION_LOCKED_BOOT_COMPLETED) return
        RestartScheduleAlarmsUseCase()
    }
}
