package com.chrrissoft.alarmmanager.onetime.usecases.classes

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.onetime.db.OneTimeAlarmDao
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm.Companion.asDb
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.DeleteOneTimeAlarmUseCase
import com.chrrissoft.alarmmanager.utils.FlowUtils.ResFlow
import com.chrrissoft.alarmmanager.utils.Util.Try
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteOneTimeAlarmUseCaseImpl @Inject constructor(
    private val dao: OneTimeAlarmDao,
) : DeleteOneTimeAlarmUseCase {
    override fun invoke(vararg data: OneTimeAlarm): Flow<ResState<*>> {
        return ResFlow {
            Try { dao.delete(*data.map { it.asDb }.toTypedArray()) }
            emit(ResState.Success(null))
        }
    }
}
