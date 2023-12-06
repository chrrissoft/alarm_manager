package com.chrrissoft.alarmmanager.app

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.chrrissoft.alarmmanager.usecases.interfaces.OnAlarmUseCase
import com.chrrissoft.alarmmanager.utils.IntentUtils.getAlarm
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.json.Json
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("MissingPermission")
class OnAlarmReceiver : BroadcastReceiver() {
    @Inject
    lateinit var OnAlarmUseCase: OnAlarmUseCase
    @Inject
    lateinit var json: Json

    override fun onReceive(context: Context?, intent: Intent?) {
        val alarm = intent?.getAlarm(json) ?: return
        OnAlarmUseCase(alarm)
    }
}
