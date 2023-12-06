package com.chrrissoft.alarmmanager.window.usecases.interfaces

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm
import kotlinx.coroutines.flow.Flow

interface ScheduleWindowAlarmsUseCase {
    operator fun invoke(vararg data: WindowAlarm) : Flow<ResState<Map<WindowAlarm, ResState<*>>>>

    operator fun invoke(data: List<WindowAlarm>) : Flow<ResState<Map<WindowAlarm, ResState<*>>>> {
        return invoke(*data.toTypedArray())
    }
}
