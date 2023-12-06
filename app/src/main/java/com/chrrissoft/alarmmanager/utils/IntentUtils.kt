package com.chrrissoft.alarmmanager.utils

import android.content.Intent
import com.chrrissoft.alarmmanager.Constants
import com.chrrissoft.alarmmanager.base.Alarm
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object IntentUtils {
    fun Intent.getAlarm(json: Json): Alarm? = getStringExtra(Constants.EXTRA_ALARM)
        ?.let { json.decodeFromString(it) }

    fun Intent.putAlarm(json: Json, alarm: Alarm): Intent {
        return putExtra(Constants.EXTRA_ALARM, json.encodeToString(alarm))
    }
}

