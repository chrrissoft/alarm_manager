package com.chrrissoft.alarmmanager.onetime.usecases.classes

import android.annotation.SuppressLint
import androidx.core.app.NotificationManagerCompat
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.entities.ResState.Companion.map
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.GetOneTimeAlarmsUseCase
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.RestartScheduledOneTimeAlarmUseCase
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.ScheduleOneTimeAlarmsUseCase
import com.chrrissoft.alarmmanager.utils.NotificationManagerUtils.scheduledAlarmNotification
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

@SuppressLint("MissingPermission")
class RestartScheduledOneTimeAlarmUseCaseImpl @Inject constructor(
    private val app: AlarmManagerApp,
    private val notificationManager: NotificationManagerCompat,
    private val GetOneTimeAlarmsUseCase: GetOneTimeAlarmsUseCase,
    private val ScheduleOneTimeUseCase: ScheduleOneTimeAlarmsUseCase,
) : RestartScheduledOneTimeAlarmUseCase {
    override suspend fun invoke() {
        return GetOneTimeAlarmsUseCase()
            .map { db -> db.map { alarms -> alarms.filter { it.running } } }
            .mapNotNull { it as? Success }
            .map { ScheduleOneTimeUseCase(it.data).last() }
            .collect { notificationManager.scheduledAlarmNotification(app, type = "One time", it) }
    }
}
