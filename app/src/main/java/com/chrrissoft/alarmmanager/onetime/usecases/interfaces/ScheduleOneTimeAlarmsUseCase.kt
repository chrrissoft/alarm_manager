package com.chrrissoft.alarmmanager.onetime.usecases.interfaces

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
import kotlinx.coroutines.flow.Flow

interface ScheduleOneTimeAlarmsUseCase {
    operator fun invoke(vararg data: OneTimeAlarm) : Flow<ResState<Map<OneTimeAlarm, ResState<*>>>>

    operator fun invoke(data: List<OneTimeAlarm>) : Flow<ResState<Map<OneTimeAlarm, ResState<*>>>> {
        return this(*data.toTypedArray())
    }
}
