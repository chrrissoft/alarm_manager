package com.chrrissoft.alarmmanager.onetime.usecases.interfaces

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
import kotlinx.coroutines.flow.Flow

interface RestartScheduledOneTimeAlarmUseCase {
    operator fun invoke() : Flow<ResState<Map<OneTimeAlarm, ResState<*>>>>
}
