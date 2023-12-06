package com.chrrissoft.alarmmanager.repeating.usecases.classes

import android.app.AlarmManager
import android.app.AlarmManager.OnAlarmListener
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Error
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.CancelRepeatingAlarmUseCase
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.SaveRepeatingAlarmUseCase
import com.chrrissoft.alarmmanager.utils.AlarmManagerUtils.cancel
import com.chrrissoft.alarmmanager.utils.FlowUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CancelRepeatingAlarmUseCaseImpl @Inject constructor(
    private val json: Json,
    private val app: AlarmManagerApp,
    private val alarmManager: AlarmManager,
    private val listeners: MutableMap<String, OnAlarmListener>,
    private val SaveRepeatingAlarmUseCase: SaveRepeatingAlarmUseCase,
) : CancelRepeatingAlarmUseCase {
    override fun invoke(vararg data: RepeatingAlarm): Flow<ResState<Map<RepeatingAlarm, ResState<*>>>> {
        alarmManager.nextAlarmClock
        return FlowUtils.alarmsFlow { res ->
            data.forEach {
                if (alarmManager.cancel(it, json, app, listeners)) {
                    SaveRepeatingAlarmUseCase(it.copy(running = false)).collect()
                    res[it] = (Success(it))
                } else res[it] = (Error(Throwable()))
            }
        }
    }
}
