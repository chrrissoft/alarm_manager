package com.chrrissoft.alarmmanager.tags.usecases.interfaces

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.tags.db.AlarmTag
import kotlinx.coroutines.flow.Flow

interface SaveAlarmTagUseCase {
    operator fun invoke(data: AlarmTag) : Flow<ResState<AlarmTag>>
}
