package com.chrrissoft.alarmmanager.clock.usecases.classes

import android.annotation.SuppressLint
import androidx.core.app.NotificationManagerCompat
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.GetClockAlarmsUseCase
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.RestartScheduledClockAlarmUseCase
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.ScheduleClockAlarmsUseCase
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Companion.map
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.utils.AlarmUtils.countText
import com.chrrissoft.alarmmanager.utils.NotificationManagerUtils.generalNotification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject
import kotlin.random.Random

@SuppressLint("MissingPermission")
class RestartScheduledClockAlarmUseCaseImpl @Inject constructor(
    private val app: AlarmManagerApp,
    private val notificationManager: NotificationManagerCompat,
    private val GetClockAlarmsUseCase: GetClockAlarmsUseCase,
    private val ScheduleClockUseCase: ScheduleClockAlarmsUseCase,
) : RestartScheduledClockAlarmUseCase {
    override fun invoke(): Flow<ResState<Map<ClockAlarm, ResState<*>>>> {
        return GetClockAlarmsUseCase()
            .map { db -> db.map { alarms -> alarms.filter { it.running } } }
            .mapNotNull { it as? Success }
            .map { alarms ->
                val res = ScheduleClockUseCase(alarms.data).last()
                val notificationText = res.countText(success = "alarms scheduled of")
                val notification = generalNotification(app, notificationText, subText = "Clock")
                notificationManager.notify(ID, notification.build())
                res
            }
    }

    companion object {
        private val ID = Random.nextInt()
    }
}
