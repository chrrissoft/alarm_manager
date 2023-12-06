package com.chrrissoft.alarmmanager.tags.usecases.classes

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.tags.db.AlarmTag
import com.chrrissoft.alarmmanager.tags.db.AlarmTagsDao
import com.chrrissoft.alarmmanager.tags.usecases.interfaces.SaveAlarmTagUseCase
import com.chrrissoft.alarmmanager.utils.FlowUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveAlarmTagUseCaseImpl @Inject constructor(
    private val dao: AlarmTagsDao,
) : SaveAlarmTagUseCase {
    override fun invoke(data: AlarmTag): Flow<ResState<AlarmTag>> {
        return FlowUtils.ResFlow { dao.add(data).apply { emit(ResState.Success(data)) } }
    }
}
