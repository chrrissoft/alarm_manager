package com.chrrissoft.alarmmanager.repeating.usecases.classes

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.repeating.db.RepeatingAlarm.Companion.asCommon
import com.chrrissoft.alarmmanager.repeating.db.RepeatingAlarmDao
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.GetRepeatingAlarmsUseCase
import com.chrrissoft.alarmmanager.utils.FlowUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepeatingAlarmsUseCaseImpl @Inject constructor(
    private val dao: RepeatingAlarmDao
) : GetRepeatingAlarmsUseCase {
    override fun invoke(): Flow<ResState<List<RepeatingAlarm>>> {
        return FlowUtils.ResFlow { dao.get().collect { emit(Success(it.asCommon)) } }
    }
}
