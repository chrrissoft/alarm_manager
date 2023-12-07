package com.chrrissoft.alarmmanager.usecases.classes

import android.content.pm.PackageManager
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.N
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.GetClockAlarmsUseCase
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.GetOneTimeAlarmsUseCase
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.GetRepeatingAlarmsUseCase
import com.chrrissoft.alarmmanager.usecases.interfaces.EnableBootReceiversOnDemandUseCase
import com.chrrissoft.alarmmanager.utils.PackagerUtils.disableBootAlarmSchedulerReceiver
import com.chrrissoft.alarmmanager.utils.PackagerUtils.disableLockedBootAlarmSchedulerReceiver
import com.chrrissoft.alarmmanager.utils.PackagerUtils.enableBootAlarmSchedulerReceiver
import com.chrrissoft.alarmmanager.utils.PackagerUtils.enableLockedBootAlarmSchedulerReceiver
import com.chrrissoft.alarmmanager.window.usecases.interfaces.GetWindowAlarmsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EnableBootReceiversOnDemandUseCaseImpl @Inject constructor(
    private val app: AlarmManagerApp,
    private val scope: CoroutineScope,
    private val packageManager: PackageManager,
    private val GetOneTimeAlarmsUseCase: GetOneTimeAlarmsUseCase,
    private val GetRepeatingAlarmsUseCase: GetRepeatingAlarmsUseCase,
    private val GetWindowAlarmsUseCase: GetWindowAlarmsUseCase,
    private val GetClockAlarmsUseCase: GetClockAlarmsUseCase,
) : EnableBootReceiversOnDemandUseCase {
    override fun invoke() {
        combine(
            GetOneTimeAlarmsUseCase(),
            GetRepeatingAlarmsUseCase(),
            GetWindowAlarmsUseCase(),
            GetClockAlarmsUseCase(),
        ) { oneTime, repeating, window, clock ->
            val oneTimeList =
                (oneTime as? Success)?.data?.filter { it.running }?.size ?: return@combine
            val repeatingList =
                (repeating as? Success)?.data?.filter { it.running }?.size ?: return@combine
            val windowList =
                (window as? Success)?.data?.filter { it.running }?.size ?: return@combine
            val clockList =
                (clock as? Success)?.data?.filter { it.running }?.size ?: return@combine

            val total = oneTimeList + repeatingList + windowList + clockList
            withContext(Main) {
                if (total > 0) {
                    if (SDK_INT >= N) packageManager.enableLockedBootAlarmSchedulerReceiver(app)
                    else packageManager.enableBootAlarmSchedulerReceiver(app)
                } else {
                    if (SDK_INT >= N) packageManager.disableLockedBootAlarmSchedulerReceiver(app)
                    else packageManager.disableBootAlarmSchedulerReceiver(app)
                }
            }
        }.launchIn(scope)
    }
}