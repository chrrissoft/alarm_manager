package com.chrrissoft.alarmmanager.app

import android.app.Service
import android.content.Intent
import com.chrrissoft.alarmmanager.usecases.interfaces.DeleteCompleteAlarmUseCase
import com.chrrissoft.alarmmanager.utils.IntentUtils.getAlarm
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.json.Json
import javax.inject.Inject

@AndroidEntryPoint
class CancelRepeatingAlarmService : Service() {
    @Inject
    lateinit var DeleteCompleteAlarmUseCase: DeleteCompleteAlarmUseCase
    @Inject
    lateinit var json: Json

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val alarm = intent?.getAlarm(json) ?: return START_NOT_STICKY
        DeleteCompleteAlarmUseCase(alarm)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?) = null
}
