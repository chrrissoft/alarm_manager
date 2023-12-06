package com.chrrissoft.alarmmanager.window.usecases.interfaces

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm
import kotlinx.coroutines.flow.Flow

interface RestartScheduledWindowAlarmsUseCase {
    operator fun invoke() : Flow<ResState<Map<WindowAlarm, ResState<*>>>>
}
