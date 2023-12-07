package com.chrrissoft.alarmmanager.utils

import android.content.Intent
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.S
import android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
import androidx.activity.ComponentActivity
import com.chrrissoft.alarmmanager.utils.Util.Try

object ActivityUtils {
    fun ComponentActivity.startExactsAlarmPermissionActivity() {
        if (SDK_INT < S) return
        Try(ctx = this) {
            Intent(ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                .apply { data = Uri.parse("package:$packageName") }
        }
    }
}
