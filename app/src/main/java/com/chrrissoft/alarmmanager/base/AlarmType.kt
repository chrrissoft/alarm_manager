package com.chrrissoft.alarmmanager.base

import android.app.AlarmManager
import com.chrrissoft.alarmmanager.Util.ui

enum class AlarmType(val original: Int) {
    RTC_WAKEUP(AlarmManager.RTC_WAKEUP),
    RTC(AlarmManager.RTC),
    ELAPSED_REALTIME_WAKEUP(AlarmManager.ELAPSED_REALTIME_WAKEUP),
    ELAPSED_REALTIME(AlarmManager.ELAPSED_REALTIME),
    ;

    val label = name.ui

    companion object {
        val list = buildList {
            add(RTC_WAKEUP)
            add(RTC)
            add(ELAPSED_REALTIME_WAKEUP)
            add(ELAPSED_REALTIME)
        }
    }
}
