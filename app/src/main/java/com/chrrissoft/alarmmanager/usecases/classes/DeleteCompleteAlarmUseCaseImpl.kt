package com.chrrissoft.alarmmanager.usecases.classes

import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.base.Alarm
import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.DeleteClockAlarmUseCase
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.DeleteOneTimeAlarmUseCase
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.CancelRepeatingAlarmUseCase
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.DeleteRepeatingAlarmUseCase
import com.chrrissoft.alarmmanager.usecases.interfaces.DeleteCompleteAlarmUseCase
import com.chrrissoft.alarmmanager.utils.ContextUtils.toastSuspend
import com.chrrissoft.alarmmanager.utils.Util.onSuccess
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm
import com.chrrissoft.alarmmanager.window.usecases.interfaces.DeleteWindowAlarmUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeleteCompleteAlarmUseCaseImpl @Inject constructor(
    private val app: AlarmManagerApp,
    private val scope: CoroutineScope,
    private val DeleteOneTimeAlarmUseCase: DeleteOneTimeAlarmUseCase,
    private val DeleteRepeatingAlarmUseCase: DeleteRepeatingAlarmUseCase,
    private val DeleteWindowAlarmUseCase: DeleteWindowAlarmUseCase,
    private val DeleteClockAlarmUseCase: DeleteClockAlarmUseCase,
    private val CancelRepeatingAlarmUseCase: CancelRepeatingAlarmUseCase,
) : DeleteCompleteAlarmUseCase {
    override operator fun invoke(data: Alarm) {
        when (data) {
            is OneTimeAlarm -> DeleteOneTimeAlarmUseCase(data).launchIn(scope)
            is RepeatingAlarm -> {
                scope.launch {
                    CancelRepeatingAlarmUseCase(data).last()
                        .onSuccess { app.toastSuspend(message = "Repeating cancelled") }
                    DeleteRepeatingAlarmUseCase(data).last()
                        .onSuccess { app.toastSuspend(message = "Repeating deleted") }
                }
            }

            is WindowAlarm -> DeleteWindowAlarmUseCase(data).launchIn(scope)
            is ClockAlarm -> DeleteClockAlarmUseCase(data).launchIn(scope)
        }
    }
}
