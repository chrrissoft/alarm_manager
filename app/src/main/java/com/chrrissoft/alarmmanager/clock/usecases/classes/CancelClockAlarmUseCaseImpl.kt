package com.chrrissoft.alarmmanager.clock.usecases.classes

import android.app.AlarmManager
import android.app.AlarmManager.OnAlarmListener
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.CancelClockAlarmUseCase
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.SaveClockAlarmUseCase
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Error
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.utils.AlarmManagerUtils.cancel
import com.chrrissoft.alarmmanager.utils.FlowUtils.alarmsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CancelClockAlarmUseCaseImpl @Inject constructor(
    private val json: Json,
    private val app: AlarmManagerApp,
    private val alarmManager: AlarmManager,
    private val SaveClockAlarmUseCase: SaveClockAlarmUseCase,
    private val listeners: MutableMap<String, OnAlarmListener>,
) : CancelClockAlarmUseCase {
    override operator fun invoke(vararg data: ClockAlarm): Flow<ResState<Map<ClockAlarm, ResState<*>>>> {
        return alarmsFlow { res ->
            data.forEach {
                if (alarmManager.cancel(it, json, app, listeners)) {
                    SaveClockAlarmUseCase(it.copy(running = false)).collect()
                    res[it] = (Success(it))
                } else res[it] = (Error(Throwable()))
            }
        }
    }
}
