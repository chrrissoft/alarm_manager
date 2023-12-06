package com.chrrissoft.alarmmanager.usecases.interfaces

import com.chrrissoft.alarmmanager.base.Alarm

interface OnAlarmUseCase {
    operator fun invoke(data: Alarm)
}
