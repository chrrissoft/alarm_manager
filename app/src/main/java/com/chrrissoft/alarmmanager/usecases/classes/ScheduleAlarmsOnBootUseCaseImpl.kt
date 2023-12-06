package com.chrrissoft.alarmmanager.usecases.classes

import com.chrrissoft.alarmmanager.clock.usecases.interfaces.RestartScheduledClockAlarmUseCase
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.RestartScheduledOneTimeAlarmUseCase
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.RestartScheduledRepeatingAlarmsUseCase
import com.chrrissoft.alarmmanager.usecases.interfaces.ScheduleAlarmsOnBootUseCase
import com.chrrissoft.alarmmanager.window.usecases.interfaces.RestartScheduledWindowAlarmsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScheduleAlarmsOnBootUseCaseImpl @Inject constructor(
    private val scope: CoroutineScope,
    private val RestartScheduledOneTimeAlarmUseCase: RestartScheduledOneTimeAlarmUseCase,
    private val RestartScheduledRepeatingAlarmsUseCase: RestartScheduledRepeatingAlarmsUseCase,
    private val RestartScheduledWindowAlarmsUseCase: RestartScheduledWindowAlarmsUseCase,
    private val RestartScheduledClockAlarmsUseCase: RestartScheduledClockAlarmUseCase,
) : ScheduleAlarmsOnBootUseCase {
    override fun invoke() {
        scope.launch {
            RestartScheduledOneTimeAlarmUseCase().last()
            RestartScheduledRepeatingAlarmsUseCase().last()
            RestartScheduledWindowAlarmsUseCase().last()
            RestartScheduledClockAlarmsUseCase().last()
        }
    }
}
