package com.chrrissoft.alarmmanager.clock.usecases.classes

import android.annotation.SuppressLint
import android.app.AlarmManager
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.SaveClockAlarmUseCase
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.ScheduleClockAlarmsUseCase
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Error
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.utils.AlarmManagerUtils.setClock
import com.chrrissoft.alarmmanager.utils.FlowUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.serialization.json.Json
import javax.inject.Inject

@SuppressLint("MissingPermission")
class ScheduleClockAlarmsUseCaseImpl @Inject constructor(
    private val json: Json,
    private val app: AlarmManagerApp,
    private val alarmManager: AlarmManager,
    private val SaveClockAlarmUseCase: SaveClockAlarmUseCase,
) : ScheduleClockAlarmsUseCase {
    override fun invoke(vararg data: ClockAlarm): Flow<ResState<Map<ClockAlarm, ResState<*>>>> {
        return FlowUtils.alarmsFlow { res ->
            data.forEach { alarm ->
                try {
                    alarmManager.setClock(app, json, alarm)
                    SaveClockAlarmUseCase(alarm.copy(running = true)).collect()
                    res[alarm] = (Success(alarm))
                } catch (e: Throwable) {
                    res[alarm] = (Error(e))
                }
            }
        }
    }
}
