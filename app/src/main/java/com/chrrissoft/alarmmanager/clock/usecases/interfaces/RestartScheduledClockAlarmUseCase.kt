package com.chrrissoft.alarmmanager.clock.usecases.interfaces

import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.entities.ResState
import kotlinx.coroutines.flow.Flow

interface RestartScheduledClockAlarmUseCase {
    operator fun invoke() : Flow<ResState<Map<ClockAlarm, ResState<*>>>>
}
