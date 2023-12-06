package com.chrrissoft.alarmmanager.clock.usecases.classes

import com.chrrissoft.alarmmanager.clock.db.ClockAlarm.Companion.asCommon
import com.chrrissoft.alarmmanager.clock.db.ClockAlarmDao
import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.GetClockAlarmsUseCase
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.utils.FlowUtils.ResFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClockAlarmsUseCaseImpl @Inject constructor(
    private val dao: ClockAlarmDao
) : GetClockAlarmsUseCase {
    override fun invoke(): Flow<ResState<List<ClockAlarm>>> {
        return ResFlow { dao.get().collect { emit(Success(it.asCommon)) } }
    }
}
