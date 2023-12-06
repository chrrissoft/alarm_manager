package com.chrrissoft.alarmmanager.onetime.usecases.interfaces

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
import kotlinx.coroutines.flow.Flow

interface GetOneTimeAlarmsUseCase {
    operator fun invoke() : Flow<ResState<List<OneTimeAlarm>>>
}
