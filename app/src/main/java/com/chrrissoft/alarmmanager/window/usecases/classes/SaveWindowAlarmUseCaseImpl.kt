package com.chrrissoft.alarmmanager.window.usecases.classes

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.utils.FlowUtils.ResFlow
import com.chrrissoft.alarmmanager.window.db.WindowAlarmDao
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm.Companion.asDb
import com.chrrissoft.alarmmanager.window.usecases.interfaces.SaveWindowAlarmUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveWindowAlarmUseCaseImpl @Inject constructor(
    private val dao: WindowAlarmDao,
) : SaveWindowAlarmUseCase {
    override fun invoke(vararg data: WindowAlarm): Flow<ResState<*>> {
        return ResFlow {
            dao.save(*data.map { it.asDb }.toTypedArray())
            emit(ResState.Success(data = null))
        }
    }
}
