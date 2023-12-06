package com.chrrissoft.alarmmanager.window.usecases.interfaces

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm
import kotlinx.coroutines.flow.Flow

interface GetWindowAlarmsUseCase {
    operator fun invoke() : Flow<ResState<List<WindowAlarm>>>
}
