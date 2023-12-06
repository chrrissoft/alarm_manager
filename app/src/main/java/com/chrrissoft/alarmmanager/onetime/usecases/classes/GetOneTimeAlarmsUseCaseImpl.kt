package com.chrrissoft.alarmmanager.onetime.usecases.classes

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.onetime.db.OneTimeAlarm.Companion.asCommon
import com.chrrissoft.alarmmanager.onetime.db.OneTimeAlarmDao
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.GetOneTimeAlarmsUseCase
import com.chrrissoft.alarmmanager.utils.FlowUtils.ResFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOneTimeAlarmsUseCaseImpl @Inject constructor(
    private val dao: OneTimeAlarmDao
) : GetOneTimeAlarmsUseCase {
    override fun invoke(): Flow<ResState<List<OneTimeAlarm>>> {
        return ResFlow { dao.get().collect { emit(Success(it.asCommon)) } }
    }
}
