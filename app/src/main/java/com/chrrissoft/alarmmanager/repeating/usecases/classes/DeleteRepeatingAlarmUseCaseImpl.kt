package com.chrrissoft.alarmmanager.repeating.usecases.classes

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.repeating.db.RepeatingAlarmDao
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm.Companion.asDb
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.DeleteRepeatingAlarmUseCase
import com.chrrissoft.alarmmanager.utils.FlowUtils
import com.chrrissoft.alarmmanager.utils.Util
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteRepeatingAlarmUseCaseImpl @Inject constructor(
    private val dao: RepeatingAlarmDao,
) : DeleteRepeatingAlarmUseCase {
    override fun invoke(vararg data: RepeatingAlarm): Flow<ResState<*>> {
        return FlowUtils.ResFlow {
            data.forEach { Util.Try { dao.delete(it.asDb) } }
            emit(ResState.Success(null))
        }
    }
}
