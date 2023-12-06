package com.chrrissoft.alarmmanager.window.entities

import com.chrrissoft.alarmmanager.base.Alarm
import com.chrrissoft.alarmmanager.entities.AlarmOperation
import com.chrrissoft.alarmmanager.entities.AlarmType
import com.chrrissoft.alarmmanager.settings.LocalDateSerializer
import com.chrrissoft.alarmmanager.settings.LocalTimeSerializer
import com.chrrissoft.alarmmanager.utils.DateTimeUtils.getMillis
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.UUID
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import com.chrrissoft.alarmmanager.window.db.WindowAlarm as WindowAlarmDb

@Serializable
data class WindowAlarm(
    override val id: String = UUID.randomUUID().toString(),
    val tag: String? = null,
    val running: Boolean = false,
    @SerialName("alarm_type") val type: AlarmType = AlarmType.RTC,
    @Contextual @Serializable(with = LocalTimeSerializer::class) val time: LocalTime = LocalTime.now(),
    @Contextual @Serializable(with = LocalDateSerializer::class) val date: LocalDate = LocalDate.now(),
    val window: Duration = 10.minutes,
    override val operation: AlarmOperation = AlarmOperation.Receiver,
    override val message: String = "",
) : Alarm {
    val triggerTime = LocalDateTime.of(date, time).getMillis()
    val rawWindow = window.inWholeMilliseconds

    companion object {
        val WindowAlarm.asDb get() = WindowAlarmDb(
            id = id,
            running = running,
            message = message,
            tag = tag,
            type = type,
            window = rawWindow,
            operation = operation,
            time = time.format(DateTimeFormatter.ISO_TIME),
            date = date.format(DateTimeFormatter.ISO_DATE),
        )
    }
}
