package com.chrrissoft.alarmmanager.usecases.classes

import com.chrrissoft.alarmmanager.clock.usecases.interfaces.RestartScheduledClockAlarmUseCase
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.RestartScheduledOneTimeAlarmUseCase
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.RestartScheduledRepeatingAlarmsUseCase
import com.chrrissoft.alarmmanager.usecases.interfaces.RestartScheduleAlarmsUseCase
import com.chrrissoft.alarmmanager.window.usecases.interfaces.RestartScheduledWindowAlarmsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class RestartScheduleAlarmsUseCaseImpl @Inject constructor(
    private val scope: CoroutineScope,
    private val RestartScheduledOneTimeAlarmUseCase: RestartScheduledOneTimeAlarmUseCase,
    private val RestartScheduledRepeatingAlarmsUseCase: RestartScheduledRepeatingAlarmsUseCase,
    private val RestartScheduledWindowAlarmsUseCase: RestartScheduledWindowAlarmsUseCase,
    private val RestartScheduledClockAlarmsUseCase: RestartScheduledClockAlarmUseCase,
) : RestartScheduleAlarmsUseCase {
    override fun invoke() {
        scope.launch {
            RestartScheduledOneTimeAlarmUseCase()
            RestartScheduledRepeatingAlarmsUseCase()
            RestartScheduledWindowAlarmsUseCase()
            RestartScheduledClockAlarmsUseCase()
        }
    }
}
