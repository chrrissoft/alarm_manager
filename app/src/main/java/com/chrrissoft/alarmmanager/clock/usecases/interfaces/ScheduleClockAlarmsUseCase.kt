package com.chrrissoft.alarmmanager.clock.usecases.interfaces

import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.entities.ResState
import kotlinx.coroutines.flow.Flow

interface ScheduleClockAlarmsUseCase {
    operator fun invoke(vararg data: ClockAlarm) : Flow<ResState<Map<ClockAlarm, ResState<*>>>>

    operator fun invoke(data: List<ClockAlarm>) : Flow<ResState<Map<ClockAlarm, ResState<*>>>> {
        return this(*data.toTypedArray())
    }
}
