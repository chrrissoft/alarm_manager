package com.chrrissoft.alarmmanager.repeating.usecases.interfaces

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import kotlinx.coroutines.flow.Flow

interface DeleteRepeatingAlarmUseCase {
    operator fun invoke(vararg data: RepeatingAlarm) : Flow<ResState<*>>

    operator fun invoke(data: List<RepeatingAlarm>) : Flow<ResState<*>> {
        return invoke(*data.toTypedArray())
    }
}
