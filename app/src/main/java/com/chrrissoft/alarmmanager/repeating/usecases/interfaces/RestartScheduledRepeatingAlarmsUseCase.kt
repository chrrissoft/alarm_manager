package com.chrrissoft.alarmmanager.repeating.usecases.interfaces

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import kotlinx.coroutines.flow.Flow

interface RestartScheduledRepeatingAlarmsUseCase {
    operator fun invoke() : Flow<ResState<Map<RepeatingAlarm, ResState<*>>>>
}
