package com.chrrissoft.alarmmanager.onetime.usecases.classes

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.AlarmManager.OnAlarmListener
import androidx.core.app.NotificationManagerCompat
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Error
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.DeleteOneTimeAlarmUseCase
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.SaveOneTimeAlarmUseCase
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.ScheduleOneTimeAlarmsUseCase
import com.chrrissoft.alarmmanager.utils.AlarmManagerUtils.OnAlarmListener
import com.chrrissoft.alarmmanager.utils.AlarmManagerUtils.setOneTime
import com.chrrissoft.alarmmanager.utils.FlowUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.serialization.json.Json
import javax.inject.Inject

@SuppressLint("MissingPermission")
class ScheduleOneTimeAlarmsUseCaseImpl @Inject constructor(
    private val json: Json,
    private val app: AlarmManagerApp,
    private val scope: CoroutineScope,
    private val alarmManager: AlarmManager,
    private val notificationManager: NotificationManagerCompat,
    private val SaveOneTimeAlarmUseCase: SaveOneTimeAlarmUseCase,
    private val DeleteOneTimeAlarmUseCase: DeleteOneTimeAlarmUseCase,
    private val listeners: MutableMap<String, OnAlarmListener>,
) : ScheduleOneTimeAlarmsUseCase {
    override fun invoke(vararg data: OneTimeAlarm): Flow<ResState<Map<OneTimeAlarm, ResState<*>>>> {
        return FlowUtils.alarmsFlow { res ->
            data.forEach { alarm ->
                try {
                    val listener = OnAlarmListener(alarm, app, notificationManager, listeners) {
                        DeleteOneTimeAlarmUseCase(alarm).launchIn(scope)
                    }
                    alarmManager.setOneTime(app, json, alarm, listener)
                    SaveOneTimeAlarmUseCase(alarm.copy(running = true)).collect()
                    res[alarm] = (Success(alarm))
                } catch (e: Throwable) {
                    res[alarm] = (Error(e))
                }
            }
        }
    }
}
