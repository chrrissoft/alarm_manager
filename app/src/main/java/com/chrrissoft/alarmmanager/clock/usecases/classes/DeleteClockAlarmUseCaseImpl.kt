package com.chrrissoft.alarmmanager.clock.usecases.classes

import com.chrrissoft.alarmmanager.clock.db.ClockAlarmDao
import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm.Companion.asDb
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.DeleteClockAlarmUseCase
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.utils.FlowUtils.ResFlow
import com.chrrissoft.alarmmanager.utils.Util.Try
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteClockAlarmUseCaseImpl @Inject constructor(
    private val dao: ClockAlarmDao,
) : DeleteClockAlarmUseCase {
    override fun invoke(vararg data: ClockAlarm): Flow<ResState<*>> {
        return ResFlow {
            Try { dao.delete(*data.map { it.asDb }.toTypedArray()) }
            emit(ResState.Success(null))
        }
    }
}
