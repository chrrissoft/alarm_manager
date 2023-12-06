package com.chrrissoft.alarmmanager

import android.app.AlarmManager
import com.chrrissoft.alarmmanager.utils.Util.ui

object Constants {
    const val EXTRA_ALARM = "com.chrrissoft.alarmmanager.extra.EXTRA_ALARM"
    const val DATABASE_NAME = "alarm_manager_database"

    const val NOTIFICATION_CHANNEL_ID = "com.intentscompanion.intents"
    const val NOTIFICATION_CHANNEL_NAME = "Notifications"

    val INEXACT_INTERVALS = buildMap {
        put("INTERVAL_FIFTEEN_MINUTES".ui, AlarmManager.INTERVAL_FIFTEEN_MINUTES)
        put("INTERVAL_HALF_HOUR".ui, AlarmManager.INTERVAL_HALF_HOUR)
        put("INTERVAL_HOUR".ui, AlarmManager.INTERVAL_HOUR)
        put("INTERVAL_HALF_DAY".ui, AlarmManager.INTERVAL_HALF_DAY)
        put("INTERVAL_DAY".ui, AlarmManager.INTERVAL_DAY)
    }.toList()
}
