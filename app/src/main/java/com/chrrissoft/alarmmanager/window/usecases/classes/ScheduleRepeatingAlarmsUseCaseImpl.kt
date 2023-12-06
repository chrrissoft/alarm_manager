package com.chrrissoft.alarmmanager.window.usecases.classes

import android.annotation.SuppressLint
import android.app.AlarmManager
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Error
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm
import com.chrrissoft.alarmmanager.window.usecases.interfaces.SaveWindowAlarmUseCase
import com.chrrissoft.alarmmanager.window.usecases.interfaces.ScheduleWindowAlarmsUseCase
import com.chrrissoft.alarmmanager.utils.AlarmManagerUtils.setWindow
import com.chrrissoft.alarmmanager.utils.FlowUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.serialization.json.Json
import javax.inject.Inject

@SuppressLint("MissingPermission")
class ScheduleWindowAlarmsUseCaseImpl @Inject constructor(
    private val json: Json,
    private val app: AlarmManagerApp,
    private val alarmManager: AlarmManager,
    private val SaveWindowAlarmUseCase: SaveWindowAlarmUseCase,
) : ScheduleWindowAlarmsUseCase {
    override fun invoke(vararg data: WindowAlarm): Flow<ResState<Map<WindowAlarm, ResState<*>>>> {
        return FlowUtils.alarmsFlow { res ->
            data.forEach { alarm ->
                try {
                    alarmManager.setWindow(app, json, alarm)
                    SaveWindowAlarmUseCase(alarm.copy(running = true)).collect()
                    res[alarm] = (Success(alarm))
                } catch (e: Throwable) {
                    e.printStackTrace()
                    res[alarm] = (Error(e))
                }
            }
        }
    }
}
