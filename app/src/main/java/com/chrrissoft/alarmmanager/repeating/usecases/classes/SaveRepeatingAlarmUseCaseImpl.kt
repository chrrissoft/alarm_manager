package com.chrrissoft.alarmmanager.repeating.usecases.classes

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.repeating.db.RepeatingAlarmDao
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm.Companion.asDb
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.SaveRepeatingAlarmUseCase
import com.chrrissoft.alarmmanager.utils.FlowUtils.ResFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveRepeatingAlarmUseCaseImpl @Inject constructor(
    private val dao: RepeatingAlarmDao,
) : SaveRepeatingAlarmUseCase {
    override fun invoke(vararg data: RepeatingAlarm): Flow<ResState<*>> {
        return ResFlow {
            dao.save(*data.map { it.asDb }.toTypedArray())
            emit(ResState.Success(data = null))
        }
    }
}
