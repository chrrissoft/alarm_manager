package com.chrrissoft.alarmmanager.utils

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_DISABLED
import android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_ENABLED
import com.chrrissoft.alarmmanager.app.ScheduleAlarmsOnBootLockedReceiver
import com.chrrissoft.alarmmanager.app.ScheduleAlarmsOnBootReceiver
import com.chrrissoft.alarmmanager.utils.ContextUtils.toast

object PackagerUtils {
    fun PackageManager.enableBootAlarmSchedulerReceiver(ctx: Context) {
        changeStateBootAlarmSchedulerReceiver(ctx, COMPONENT_ENABLED_STATE_ENABLED)
            .let { if (it) ctx.toast(message = "Boot receiver enabled") }
    }

    fun PackageManager.disableBootAlarmSchedulerReceiver(ctx: Context) {
        changeStateBootAlarmSchedulerReceiver(ctx, COMPONENT_ENABLED_STATE_DISABLED)
            .let { if (it) ctx.toast(message = "Boot receiver disabled") }
    }

    private fun PackageManager.changeStateBootAlarmSchedulerReceiver(
        ctx: Context, state: Int, flags: Int = PackageManager.DONT_KILL_APP
    ): Boolean {
        val component = ComponentName(ctx, ScheduleAlarmsOnBootReceiver::class.java)
        if (getComponentEnabledSetting(component) == state) return false
        setComponentEnabledSetting(component, state, flags)
        return true
    }


    fun PackageManager.enableLockedBootAlarmSchedulerReceiver(ctx: Context) {
        changeStateLockedBootAlarmSchedulerReceiver(ctx, COMPONENT_ENABLED_STATE_ENABLED)
            .let { if (it) ctx.toast(message = "Locked boot receiver enabled") }
    }

    fun PackageManager.disableLockedBootAlarmSchedulerReceiver(ctx: Context) {
        changeStateLockedBootAlarmSchedulerReceiver(ctx, COMPONENT_ENABLED_STATE_DISABLED)
            .let { if (it) ctx.toast(message = "Locked boot receiver disabled") }
    }

    private fun PackageManager.changeStateLockedBootAlarmSchedulerReceiver(
        ctx: Context, state: Int, flags: Int = PackageManager.DONT_KILL_APP
    ): Boolean {
        val component = ComponentName(ctx, ScheduleAlarmsOnBootLockedReceiver::class.java)
        if (getComponentEnabledSetting(component) == state) return false
        setComponentEnabledSetting(component, state, flags)
        return true
    }
}