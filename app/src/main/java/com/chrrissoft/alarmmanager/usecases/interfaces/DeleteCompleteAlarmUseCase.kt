package com.chrrissoft.alarmmanager.usecases.interfaces

import com.chrrissoft.alarmmanager.base.Alarm

interface DeleteCompleteAlarmUseCase {
    operator fun invoke(data: Alarm)
}
