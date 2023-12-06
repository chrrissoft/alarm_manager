package com.chrrissoft.alarmmanager.window.usecases.classes

import android.annotation.SuppressLint
import androidx.core.app.NotificationManagerCompat
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Companion.map
import com.chrrissoft.alarmmanager.utils.AlarmUtils.countText
import com.chrrissoft.alarmmanager.utils.NotificationManagerUtils.generalNotification
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm
import com.chrrissoft.alarmmanager.window.usecases.interfaces.GetWindowAlarmsUseCase
import com.chrrissoft.alarmmanager.window.usecases.interfaces.RestartScheduledWindowAlarmsUseCase
import com.chrrissoft.alarmmanager.window.usecases.interfaces.ScheduleWindowAlarmsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject
import kotlin.random.Random

@SuppressLint("MissingPermission")
class RestartScheduledWindowAlarmsUseCaseImpl @Inject constructor(
    private val app: AlarmManagerApp,
    private val notificationManager: NotificationManagerCompat,
    private val GetWindowAlarmsUseCase: GetWindowAlarmsUseCase,
    private val ScheduleWindowAlarmsUseCase: ScheduleWindowAlarmsUseCase,
) : RestartScheduledWindowAlarmsUseCase {
    override fun invoke(): Flow<ResState<Map<WindowAlarm, ResState<*>>>> {
        return GetWindowAlarmsUseCase()
            .map { db -> db.map { alarms -> alarms.filter { it.running } } }
            .mapNotNull { it as? ResState.Success }
            .map { alarms ->
                val res = ScheduleWindowAlarmsUseCase(alarms.data).last()
                val notificationText = res.countText(success = "alarms scheduled of")
                val notification = generalNotification(app, notificationText, subText = "Window")
                notificationManager.notify(ID, notification.build())
                res
            }
    }

    companion object {
        private val ID = Random.nextInt()
    }
}
