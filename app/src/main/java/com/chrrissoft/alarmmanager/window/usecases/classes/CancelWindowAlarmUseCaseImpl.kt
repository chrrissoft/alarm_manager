package com.chrrissoft.alarmmanager.window.usecases.classes

import android.app.AlarmManager
import android.app.AlarmManager.OnAlarmListener
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Error
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.utils.AlarmManagerUtils.cancel
import com.chrrissoft.alarmmanager.utils.FlowUtils
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm
import com.chrrissoft.alarmmanager.window.usecases.interfaces.CancelWindowAlarmUseCase
import com.chrrissoft.alarmmanager.window.usecases.interfaces.SaveWindowAlarmUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CancelWindowAlarmUseCaseImpl @Inject constructor(
    private val json: Json,
    private val app: AlarmManagerApp,
    private val alarmManager: AlarmManager,
    private val listeners: MutableMap<String, OnAlarmListener>,
    private val SaveWindowAlarmUseCase: SaveWindowAlarmUseCase,
) : CancelWindowAlarmUseCase {
    override fun invoke(vararg data: WindowAlarm): Flow<ResState<Map<WindowAlarm, ResState<*>>>> {
        return FlowUtils.alarmsFlow { res ->
            data.forEach {
                if (alarmManager.cancel(it, json, app, listeners)) {
                    SaveWindowAlarmUseCase(it.copy(running = false)).collect()
                    res[it] = (Success(it))
                } else res[it] = (Error(Throwable()))
            }
        }
    }
}
