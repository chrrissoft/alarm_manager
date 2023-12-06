package com.chrrissoft.alarmmanager.onetime.usecases.interfaces

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
import kotlinx.coroutines.flow.Flow

interface SaveOneTimeAlarmUseCase {
    operator fun invoke(vararg data: OneTimeAlarm) : Flow<ResState<*>>

    operator fun invoke(data: List<OneTimeAlarm>) : Flow<ResState<*>> {
        return invoke(*data.toTypedArray())
    }
}
