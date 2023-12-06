package com.chrrissoft.alarmmanager.usecases.classes

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.app.NotificationManagerCompat
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.base.Alarm
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import com.chrrissoft.alarmmanager.usecases.interfaces.DeleteCompleteAlarmUseCase
import com.chrrissoft.alarmmanager.usecases.interfaces.OnAlarmUseCase
import com.chrrissoft.alarmmanager.utils.NotificationManagerUtils
import com.chrrissoft.alarmmanager.utils.NotificationManagerUtils.setCancelRepeatingAlarmAction
import com.chrrissoft.alarmmanager.utils.Util.debug
import kotlinx.serialization.json.Json
import javax.inject.Inject
import kotlin.random.Random

class OnAlarmUseCaseImpl @Inject constructor(
    private val json: Json,
    private val app: AlarmManagerApp,
    private val notificationManager: NotificationManagerCompat,
    private val DeleteCompleteAlarmUseCase: DeleteCompleteAlarmUseCase,
) : OnAlarmUseCase {
    override fun invoke(data: Alarm) {
        debug(data)
        notify(data, app, json)
        delete(data)
    }

    @SuppressLint("MissingPermission")
    private fun notify(alarm: Alarm, context: Context, json: Json) {
        NotificationManagerUtils.generalNotification(context, title = alarm.message)
            .setCancelRepeatingAlarmAction(alarm, json, context)
            .build().let { notificationManager.notify(Random.nextInt(), it) }
    }

    private fun delete(alarm: Alarm) {
        if (alarm is RepeatingAlarm) return
        DeleteCompleteAlarmUseCase(alarm)
    }
}
