package com.chrrissoft.alarmmanager

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import com.chrrissoft.alarmmanager.Constants.NOTIFICATION_CHANNEL_ID
import com.chrrissoft.alarmmanager.Constants.NOTIFICATION_CHANNEL_NAME
import com.chrrissoft.alarmmanager.utils.ContextUtils.toast
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

@HiltAndroidApp
@SuppressLint("MissingPermission")
class AlarmManagerApp : Application() {
    @Inject
    lateinit var notificationManager: NotificationManagerCompat

    private val handler = CoroutineExceptionHandler { _, e ->
        this.toast(message = e.message ?: "Unknown error", Toast.LENGTH_LONG)
        e.printStackTrace()
    }

    val scope = CoroutineScope(SupervisorJob() + handler)

    override fun onCreate() {
        super.onCreate()
        createChannel()
        AndroidThreeTen.init(this)
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
    }
}
