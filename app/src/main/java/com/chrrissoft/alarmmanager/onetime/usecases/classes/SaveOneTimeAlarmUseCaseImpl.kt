package com.chrrissoft.alarmmanager.onetime.usecases.classes

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.onetime.db.OneTimeAlarmDao
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm.Companion.asDb
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.SaveOneTimeAlarmUseCase
import com.chrrissoft.alarmmanager.utils.FlowUtils.ResFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveOneTimeAlarmUseCaseImpl @Inject constructor(
    private val dao: OneTimeAlarmDao,
) : SaveOneTimeAlarmUseCase {
    override fun invoke(vararg data: OneTimeAlarm): Flow<ResState<*>> {
        return ResFlow {
            dao.save(*data.map { it.asDb }.toTypedArray())
            emit(ResState.Success(data = null))
        }
    }
}
