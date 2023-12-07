package com.chrrissoft.alarmmanager.window.usecases.classes

import android.annotation.SuppressLint
import androidx.core.app.NotificationManagerCompat
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Companion.map
import com.chrrissoft.alarmmanager.utils.NotificationManagerUtils.scheduledAlarmNotification
import com.chrrissoft.alarmmanager.window.usecases.interfaces.GetWindowAlarmsUseCase
import com.chrrissoft.alarmmanager.window.usecases.interfaces.RestartScheduledWindowAlarmsUseCase
import com.chrrissoft.alarmmanager.window.usecases.interfaces.ScheduleWindowAlarmsUseCase
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

@SuppressLint("MissingPermission")
class RestartScheduledWindowAlarmsUseCaseImpl @Inject constructor(
    private val app: AlarmManagerApp,
    private val notificationManager: NotificationManagerCompat,
    private val GetWindowAlarmsUseCase: GetWindowAlarmsUseCase,
    private val ScheduleWindowAlarmsUseCase: ScheduleWindowAlarmsUseCase,
) : RestartScheduledWindowAlarmsUseCase {
    override suspend fun invoke() {
        return GetWindowAlarmsUseCase()
            .map { db -> db.map { alarms -> alarms.filter { it.running } } }
            .mapNotNull { it as? ResState.Success }
            .map { ScheduleWindowAlarmsUseCase(it.data).last() }
            .collect { notificationManager.scheduledAlarmNotification(app, type = "Window", it) }
    }
}
