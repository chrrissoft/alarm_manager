package com.chrrissoft.alarmmanager.app

import android.app.AlarmManager
import android.app.AlarmManager.ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.S
import androidx.annotation.RequiresApi
import com.chrrissoft.alarmmanager.usecases.interfaces.RestartScheduleAlarmsUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScheduleExactAlarmPermissionChangedReceiver @Inject constructor(
    private val alarmManager: AlarmManager,
    private val RestartScheduleAlarmsUseCase: RestartScheduleAlarmsUseCase
) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (SDK_INT < S) return
        if (intent?.action != ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED) return
        if (!alarmManager.canScheduleExactAlarms()) return
        RestartScheduleAlarmsUseCase()
    }

    companion object {
        @RequiresApi(S)
        val filter = IntentFilter(ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED)
    }
}
