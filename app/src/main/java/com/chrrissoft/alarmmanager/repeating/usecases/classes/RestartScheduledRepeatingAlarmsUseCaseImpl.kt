package com.chrrissoft.alarmmanager.repeating.usecases.classes

import android.annotation.SuppressLint
import androidx.core.app.NotificationManagerCompat
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Companion.map
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.GetRepeatingAlarmsUseCase
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.RestartScheduledRepeatingAlarmsUseCase
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.ScheduleRepeatingAlarmsUseCase
import com.chrrissoft.alarmmanager.utils.NotificationManagerUtils.scheduledAlarmNotification
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

@SuppressLint("MissingPermission")
class RestartScheduledRepeatingAlarmsUseCaseImpl @Inject constructor(
    private val app: AlarmManagerApp,
    private val notificationManager: NotificationManagerCompat,
    private val GetRepeatingAlarmsUseCase: GetRepeatingAlarmsUseCase,
    private val ScheduleRepeatingAlarmsUseCase: ScheduleRepeatingAlarmsUseCase,
) : RestartScheduledRepeatingAlarmsUseCase {
    override suspend fun invoke() {
        return GetRepeatingAlarmsUseCase()
            .map { db -> db.map { alarms -> alarms.filter { it.running } } }
            .mapNotNull { it as? ResState.Success }
            .map { ScheduleRepeatingAlarmsUseCase(it.data).last() }
            .collect { notificationManager.scheduledAlarmNotification(app, type = "Repeating", it) }
    }
}
