package com.chrrissoft.alarmmanager.window.usecases.classes

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.utils.FlowUtils
import com.chrrissoft.alarmmanager.window.db.WindowAlarm.Companion.asCommon
import com.chrrissoft.alarmmanager.window.db.WindowAlarmDao
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm
import com.chrrissoft.alarmmanager.window.usecases.interfaces.GetWindowAlarmsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWindowAlarmsUseCaseImpl @Inject constructor(
    private val dao: WindowAlarmDao
) : GetWindowAlarmsUseCase {
    override fun invoke(): Flow<ResState<List<WindowAlarm>>> {
        return FlowUtils.ResFlow { dao.get().collect { emit(Success(it.asCommon)) } }
    }
}
