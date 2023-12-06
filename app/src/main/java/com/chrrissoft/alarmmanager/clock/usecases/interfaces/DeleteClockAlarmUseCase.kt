package com.chrrissoft.alarmmanager.clock.usecases.interfaces

import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.entities.ResState
import kotlinx.coroutines.flow.Flow

interface DeleteClockAlarmUseCase {
    operator fun invoke(vararg data: ClockAlarm) : Flow<ResState<*>>

    operator fun invoke(data: List<ClockAlarm>) : Flow<ResState<*>> {
        return this(*data.toTypedArray())
    }
}
