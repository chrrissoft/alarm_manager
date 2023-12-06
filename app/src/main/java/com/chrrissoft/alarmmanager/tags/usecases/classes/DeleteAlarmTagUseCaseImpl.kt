package com.chrrissoft.alarmmanager.tags.usecases.classes

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.tags.db.AlarmTag
import com.chrrissoft.alarmmanager.tags.db.AlarmTagsDao
import com.chrrissoft.alarmmanager.tags.usecases.interfaces.DeleteAlarmTagUseCase
import com.chrrissoft.alarmmanager.utils.FlowUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteAlarmTagUseCaseImpl @Inject constructor(
    private val dao: AlarmTagsDao,
) : DeleteAlarmTagUseCase {
    override fun invoke(data: AlarmTag): Flow<ResState<*>> {
        return FlowUtils.ResFlow { dao.delete(data).apply { emit(ResState.Success(null)) } }
    }
}
