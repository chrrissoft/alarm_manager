package com.chrrissoft.alarmmanager.tags.usecases.classes

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.tags.db.AlarmTag
import com.chrrissoft.alarmmanager.tags.db.AlarmTagsDao
import com.chrrissoft.alarmmanager.tags.usecases.interfaces.GetAlarmTagsUseCase
import com.chrrissoft.alarmmanager.utils.FlowUtils.ResFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlarmTagsUseCaseImpl @Inject constructor(
    private val dao: AlarmTagsDao
) : GetAlarmTagsUseCase {
    override fun invoke(): Flow<ResState<List<AlarmTag>>> {
        return ResFlow { dao.get().collect { emit(Success(it)) } }
    }
}
