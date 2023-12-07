package com.chrrissoft.alarmmanager.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_BOOT_COMPLETED
import com.chrrissoft.alarmmanager.usecases.interfaces.RestartScheduleAlarmsUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScheduleAlarmsOnBootReceiver : BroadcastReceiver() {
    @Inject
    lateinit var RestartScheduleAlarmsUseCase: RestartScheduleAlarmsUseCase

    override fun onReceive(context: Context?, intent: Intent?) {
        intent ?: return
        if (intent.action != ACTION_BOOT_COMPLETED) return
        RestartScheduleAlarmsUseCase()
    }
}
