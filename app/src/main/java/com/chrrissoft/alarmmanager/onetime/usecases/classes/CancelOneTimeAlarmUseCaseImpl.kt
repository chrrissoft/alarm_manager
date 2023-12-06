package com.chrrissoft.alarmmanager.onetime.usecases.classes

import android.app.AlarmManager
import android.app.AlarmManager.OnAlarmListener
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Error
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.CancelOneTimeAlarmUseCase
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.SaveOneTimeAlarmUseCase
import com.chrrissoft.alarmmanager.utils.AlarmManagerUtils.cancel
import com.chrrissoft.alarmmanager.utils.FlowUtils.alarmsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CancelOneTimeAlarmUseCaseImpl @Inject constructor(
    private val json: Json,
    private val app: AlarmManagerApp,
    private val alarmManager: AlarmManager,
    private val SaveOneTimeAlarmUseCase: SaveOneTimeAlarmUseCase,
    private val listeners: MutableMap<String, OnAlarmListener>,
) : CancelOneTimeAlarmUseCase {
    override operator fun invoke(vararg data: OneTimeAlarm): Flow<ResState<Map<OneTimeAlarm, ResState<*>>>> {
        return alarmsFlow { res ->
            data.forEach {
                if (alarmManager.cancel(it, json, app, listeners)) {
                    SaveOneTimeAlarmUseCase(it.copy(running = false)).collect()
                    res[it] = (Success(it))
                } else res[it] = (Error(Throwable()))
            }
        }
    }
}
