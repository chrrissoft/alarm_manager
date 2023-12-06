package com.chrrissoft.alarmmanager.window.usecases.interfaces

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm
import kotlinx.coroutines.flow.Flow

interface DeleteWindowAlarmUseCase {
    operator fun invoke(vararg data: WindowAlarm) : Flow<ResState<*>>

    operator fun invoke(data: List<WindowAlarm>) : Flow<ResState<*>> {
        return invoke(*data.toTypedArray())
    }
}
