package com.chrrissoft.alarmmanager.clock.usecases.classes

import com.chrrissoft.alarmmanager.clock.db.ClockAlarmDao
import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm.Companion.asDb
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.SaveClockAlarmUseCase
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.utils.FlowUtils.ResFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveClockAlarmUseCaseImpl @Inject constructor(
    private val dao: ClockAlarmDao,
) : SaveClockAlarmUseCase {
    override fun invoke(vararg data: ClockAlarm): Flow<ResState<*>> {
        return ResFlow {
            dao.save(*data.map { it.asDb }.toTypedArray())
            emit(ResState.Success(data = null))
        }
    }
}
