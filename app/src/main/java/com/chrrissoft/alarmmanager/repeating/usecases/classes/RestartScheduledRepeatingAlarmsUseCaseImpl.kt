package com.chrrissoft.alarmmanager.repeating.usecases.classes

import android.annotation.SuppressLint
import androidx.core.app.NotificationManagerCompat
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Companion.map
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.GetRepeatingAlarmsUseCase
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.RestartScheduledRepeatingAlarmsUseCase
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.ScheduleRepeatingAlarmsUseCase
import com.chrrissoft.alarmmanager.utils.AlarmUtils.countText
import com.chrrissoft.alarmmanager.utils.NotificationManagerUtils.generalNotification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject
import kotlin.random.Random

@SuppressLint("MissingPermission")
class RestartScheduledRepeatingAlarmsUseCaseImpl @Inject constructor(
    private val app: AlarmManagerApp,
    private val notificationManager: NotificationManagerCompat,
    private val GetRepeatingAlarmsUseCase: GetRepeatingAlarmsUseCase,
    private val ScheduleRepeatingAlarmsUseCase: ScheduleRepeatingAlarmsUseCase,
) : RestartScheduledRepeatingAlarmsUseCase {
    override fun invoke(): Flow<ResState<Map<RepeatingAlarm, ResState<*>>>> {
        return GetRepeatingAlarmsUseCase()
            .map { db -> db.map { alarms -> alarms.filter { it.running } } }
            .mapNotNull { it as? ResState.Success }
            .map { alarms ->
                val res = ScheduleRepeatingAlarmsUseCase(alarms.data).last()
                val notificationText = res.countText(success = "alarms scheduled of")
                val notification = generalNotification(app, notificationText, subText = "Repeating")
                notificationManager.notify(ID, notification.build())
                res
            }
    }

    companion object {
        private val ID = Random.nextInt()
    }
}
