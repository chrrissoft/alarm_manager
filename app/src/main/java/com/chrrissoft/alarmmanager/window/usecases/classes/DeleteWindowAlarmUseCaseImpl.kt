package com.chrrissoft.alarmmanager.window.usecases.classes

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.utils.FlowUtils
import com.chrrissoft.alarmmanager.utils.Util
import com.chrrissoft.alarmmanager.window.db.WindowAlarmDao
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm.Companion.asDb
import com.chrrissoft.alarmmanager.window.usecases.interfaces.DeleteWindowAlarmUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteWindowAlarmUseCaseImpl @Inject constructor(
    private val dao: WindowAlarmDao,
) : DeleteWindowAlarmUseCase {
    override fun invoke(vararg data: WindowAlarm): Flow<ResState<*>> {
        return FlowUtils.ResFlow {
            data.forEach { Util.Try { dao.delete(it.asDb) } }
            emit(ResState.Success(null))
        }
    }
}
