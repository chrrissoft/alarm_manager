@file:Suppress("UNCHECKED_CAST")

package com.chrrissoft.alarmmanager.base

import android.app.AlarmManager
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import com.chrrissoft.alarmmanager.base.AlarmOperation.PendingIntentOperation
import com.chrrissoft.alarmmanager.base.AlarmOperation.PendingIntentOperation.Companion.list
import java.lang.System.currentTimeMillis

sealed interface AlarmBuilder<out O : AlarmOperation<*>> {
    val type: AlarmType
    val timeType: TimeType
    val time: Long
    val operation: O
    val running: Boolean


    sealed interface AlarmBuilderOpenOperation : AlarmBuilder<AlarmOperation<*>> {
        val tag: String?

        fun <A : AlarmBuilder<*>> copyAlarm(
            tag: String? = this.tag,
            timeType: TimeType = this.timeType,
            type: AlarmType = this.type,
            time: Long = this.time,
            operation: AlarmOperation<*> = this.operation,
            running: Boolean = this.running,
        ): A {
            return when (this) {
                is OneTimeBuilder -> {
                    copy(
                        tag = tag,
                        timeType = timeType,
                        type = type,
                        time = time,
                        operation = operation,
                        running = running
                    ) as A
                }
                is WindowsBuilder -> {
                    copy(
                        tag = tag,
                        timeType = timeType,
                        type = type,
                        time = time,
                        operation = operation,
                        running = running
                    ) as A
                }
            }
        }
    }

    sealed interface AlarmBuilderPendingIntentOperation : AlarmBuilder<PendingIntentOperation> {
        fun <A : AlarmBuilderPendingIntentOperation> copyAlarm(
            timeType: TimeType = this.timeType,
            type: AlarmType = this.type,
            time: Long = this.time,
            operation: PendingIntentOperation = this.operation,
            running: Boolean = this.running,
        ): A {
            return when (this) {
                is OneTimeIdleBuilder -> {
                    copy(
                        timeType = timeType,
                        type = type,
                        time = time,
                        operation = operation,
                        running = running
                    ) as A
                }
                is RepeatingBuilder -> {
                    copy(
                        timeType = timeType,
                        type = type,
                        time = time,
                        operation = operation,
                        running = running
                    ) as A
                }
            }
        }
    }


    data class OneTimeBuilder(
        override val tag: String? = null,
        override val running: Boolean = false,
        override val type: AlarmType = AlarmType.RTC,
        override val time: Long = currentTimeMillis(),
        override val timeType: TimeType = TimeType.INEXACT,
        override val operation: AlarmOperation<*> = list.first(),
    ) : AlarmBuilderOpenOperation

    data class WindowsBuilder(
        override val tag: String? = null,
        override val type: AlarmType = AlarmType.RTC,
        override val time: Long = currentTimeMillis(),
        override val timeType: TimeType = TimeType.NOT_ALLOWED,
        override val operation: AlarmOperation<*> = AlarmOperation.list.first(),
        override val running: Boolean = false,
        val window: Long = 60000,
    ) : AlarmBuilderOpenOperation

    data class RepeatingBuilder(
        override val running: Boolean = false,
        override val type: AlarmType = AlarmType.RTC,
        override val time: Long = currentTimeMillis(),
        override val timeType: TimeType = TimeType.INEXACT,
        override val operation: PendingIntentOperation = list.first(),
        val interval: Long = AlarmManager.INTERVAL_FIFTEEN_MINUTES,
    ) : AlarmBuilderPendingIntentOperation

    data class OneTimeIdleBuilder(
        override val type: AlarmType = AlarmType.RTC,
        override val time: Long = currentTimeMillis(),
        override val timeType: TimeType = TimeType.INEXACT,
        override val operation: PendingIntentOperation = list.first(),
        override val running: Boolean = false,
    ) : AlarmBuilderPendingIntentOperation

    enum class TimeType(val icon: ImageVector) {
        EXACT(Icons.Rounded.Favorite),
        INEXACT(Icons.Rounded.Favorite),
        NOT_ALLOWED(Icons.Rounded.Favorite),
        ;

        companion object {
            val types = listOf(EXACT, INEXACT)
            val nullablesTypes = listOf(EXACT, INEXACT, null)
        }
    }
}
