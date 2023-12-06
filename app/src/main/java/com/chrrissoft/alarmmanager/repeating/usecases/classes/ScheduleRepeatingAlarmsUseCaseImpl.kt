package com.chrrissoft.alarmmanager.repeating.usecases.classes

import android.annotation.SuppressLint
import android.app.AlarmManager
import androidx.core.app.NotificationManagerCompat
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Error
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.DeleteRepeatingAlarmUseCase
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.SaveRepeatingAlarmUseCase
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.ScheduleRepeatingAlarmsUseCase
import com.chrrissoft.alarmmanager.utils.AlarmManagerUtils.OnAlarmListener
import com.chrrissoft.alarmmanager.utils.AlarmManagerUtils.setRepeating
import com.chrrissoft.alarmmanager.utils.FlowUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.serialization.json.Json
import javax.inject.Inject

@SuppressLint("MissingPermission")
class ScheduleRepeatingAlarmsUseCaseImpl @Inject constructor(
    private val json: Json,
    private val app: AlarmManagerApp,
    private val scope: CoroutineScope,
    private val alarmManager: AlarmManager,
    private val notificationManager: NotificationManagerCompat,
    private val listeners: MutableMap<String, AlarmManager.OnAlarmListener>,
    private val SaveRepeatingAlarmUseCase: SaveRepeatingAlarmUseCase,
    private val DeleteRepeatingAlarmUseCase: DeleteRepeatingAlarmUseCase,
) : ScheduleRepeatingAlarmsUseCase {
    override fun invoke(vararg data: RepeatingAlarm): Flow<ResState<Map<RepeatingAlarm, ResState<*>>>> {
        return FlowUtils.alarmsFlow { res ->
            data.forEach { alarm ->
                try {
                    val listener = OnAlarmListener(alarm, app, notificationManager, listeners) {
                        DeleteRepeatingAlarmUseCase(alarm).launchIn(scope)
                    }
                    alarmManager.setRepeating(app, json, alarm, listener)
                    SaveRepeatingAlarmUseCase(alarm.copy(running = true)).collect()
                    res[alarm] = (Success(alarm))
                } catch (e: Throwable) {
                    e.printStackTrace()
                    res[alarm] = (Error(e))
                }
            }
        }
    }
}
