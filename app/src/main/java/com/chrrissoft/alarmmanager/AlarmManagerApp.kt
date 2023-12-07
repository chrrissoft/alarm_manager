package com.chrrissoft.alarmmanager

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import android.os.Build.VERSION_CODES.S
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.registerReceiver
import com.chrrissoft.alarmmanager.Constants.NOTIFICATION_CHANNEL_ID
import com.chrrissoft.alarmmanager.Constants.NOTIFICATION_CHANNEL_NAME
import com.chrrissoft.alarmmanager.app.ScheduleExactAlarmPermissionChangedReceiver
import com.chrrissoft.alarmmanager.app.ScheduleExactAlarmPermissionChangedReceiver.Companion.filter
import com.chrrissoft.alarmmanager.usecases.interfaces.EnableBootReceiversOnDemandUseCase
import com.chrrissoft.alarmmanager.utils.ContextUtils.toast
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import androidx.core.content.ContextCompat.RECEIVER_EXPORTED as RECEIVER_EXPORTED_COMPAT

@HiltAndroidApp
@SuppressLint("MissingPermission")
class AlarmManagerApp : Application() {
    @Inject
    lateinit var notificationManager: NotificationManagerCompat

    @Inject
    lateinit var EnableBootReceiversOnDemandUseCase: EnableBootReceiversOnDemandUseCase

    @Inject
    lateinit var scheduleExactsChangedReceiver: ScheduleExactAlarmPermissionChangedReceiver

    private val handler = CoroutineExceptionHandler { _, e ->
        this.toast(message = e.message ?: "Unknown error", Toast.LENGTH_LONG)
        e.printStackTrace()
    }

    val scope = CoroutineScope(SupervisorJob() + handler)

    override fun onCreate() {
        super.onCreate()
        createChannel()
        AndroidThreeTen.init(this)
        EnableBootReceiversOnDemandUseCase()
        registerScheduleExactsChangedReceiver()
    }

    private fun registerScheduleExactsChangedReceiver() {
        if (SDK_INT < S) return
        registerReceiver((this), scheduleExactsChangedReceiver, filter, RECEIVER_EXPORTED_COMPAT)
    }

    private fun createChannel() {
        if (SDK_INT < O) return
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
    }
}
