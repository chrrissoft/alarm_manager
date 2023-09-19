package com.chrrissoft.alarmmanager.base

import android.app.AlarmManager.OnAlarmListener
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import com.chrrissoft.alarmmanager.MainActivity
import com.chrrissoft.alarmmanager.MainReceiver
import com.chrrissoft.alarmmanager.MainService


sealed interface AlarmOperation<R> {
    val label: String
    val icon: ImageVector
    fun build(ctx: Context): R

    data class OpenOperation(
        override val label: String = "local operation",
        override val icon: ImageVector = Icons.Rounded.Favorite,
        val operation: OnAlarmListener? = null,
    ) : AlarmOperation<OnAlarmListener> {
        override fun build(ctx: Context): OnAlarmListener {
            return operation ?: throw IllegalStateException()
        }
    }

    data class PendingIntentOperation constructor(
        val kclass: Class<*>,
        override val label: String,
        override val icon: ImageVector,
    ) : AlarmOperation<PendingIntent> {
        override fun build(ctx: Context): PendingIntent {
            val intent = Intent(ctx, kclass)
            return when (kclass) {
                MainActivity::class.java -> PendingIntent.getActivity(
                    ctx,
                    (0),
                    intent,
                    PendingIntent.FLAG_IMMUTABLE
                )
                MainService::class.java -> PendingIntent.getActivity(
                    ctx,
                    (0),
                    intent,
                    PendingIntent.FLAG_IMMUTABLE
                )
                MainReceiver::class.java -> PendingIntent.getActivity(
                    ctx,
                    (0),
                    intent,
                    PendingIntent.FLAG_IMMUTABLE
                )
                else -> throw IllegalStateException()
            }
        }

        companion object {
            private val mainActivity = run {
                PendingIntentOperation(
                    label = "Main Activity",
                    icon = Icons.Rounded.Favorite,
                    kclass = MainActivity::class.java
                )
            }

            private val mainService = run {
                PendingIntentOperation(
                    label = "Main Service",
                    icon = Icons.Rounded.Favorite,
                    kclass = MainService::class.java
                )
            }

            private val mainReceiver = run {
                PendingIntentOperation(
                    label = "Main Receiver",
                    icon = Icons.Rounded.Favorite,
                    kclass = MainReceiver::class.java
                )
            }

            val list = buildList {
                add(mainActivity)
                add(mainService)
                add(mainReceiver)
            }
        }
    }

    companion object {
        val list = buildList {
            PendingIntentOperation.list.forEach { add(it) }
            add(OpenOperation())
        }
    }
}
