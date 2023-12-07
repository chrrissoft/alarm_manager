package com.chrrissoft.alarmmanager.clock.usecases.classes

import android.annotation.SuppressLint
import androidx.core.app.NotificationManagerCompat
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.GetClockAlarmsUseCase
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.RestartScheduledClockAlarmUseCase
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.ScheduleClockAlarmsUseCase
import com.chrrissoft.alarmmanager.entities.ResState.Companion.map
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.utils.NotificationManagerUtils.scheduledAlarmNotification
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

@SuppressLint("MissingPermission")
class RestartScheduledClockAlarmUseCaseImpl @Inject constructor(
    private val app: AlarmManagerApp,
    private val notificationManager: NotificationManagerCompat,
    private val GetClockAlarmsUseCase: GetClockAlarmsUseCase,
    private val ScheduleClockUseCase: ScheduleClockAlarmsUseCase,
) : RestartScheduledClockAlarmUseCase {
    override suspend fun invoke() {
        return GetClockAlarmsUseCase()
            .map { db -> db.map { alarms -> alarms.filter { it.running } } }
            .mapNotNull { it as? Success }
            .map { ScheduleClockUseCase(it.data).last() }
            .collect { notificationManager.scheduledAlarmNotification(app, type = "Clocks", it) }
    }
}
