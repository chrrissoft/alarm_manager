package com.chrrissoft.alarmmanager.repeating.entities

import com.chrrissoft.alarmmanager.base.Alarm
import com.chrrissoft.alarmmanager.entities.AlarmOperation
import com.chrrissoft.alarmmanager.entities.AlarmType
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
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
import com.chrrissoft.alarmmanager.repeating.db.RepeatingAlarm as RepeatingAlarmDb

@Serializable
data class RepeatingAlarm(
    override val id: String = UUID.randomUUID().toString(),
    val tag: String? = null,
    val idle: Boolean = true,
    val exact: Boolean = false,
    val running: Boolean = false,
    override val message: String = "",
    val repeating: Duration = 10.minutes,
    override val operation: AlarmOperation = AlarmOperation.Receiver,
    @SerialName("alarm_type") val type: AlarmType = AlarmType.RTC,
    @Contextual @Serializable(with = LocalTimeSerializer::class) val time: LocalTime = LocalTime.now(),
    @Contextual @Serializable(with = LocalDateSerializer::class) val date: LocalDate = LocalDate.now(),
) : Alarm {
    val triggerTime = LocalDateTime.of(date, time).getMillis()

    val rawRepeating = repeating.inWholeMilliseconds

    companion object {
        val RepeatingAlarm.asDb
            get() = RepeatingAlarmDb(
                id = id,
                running = running,
                type = type,
                message = message,
                repeating = rawRepeating,
                operation = operation,
                time = time.format(DateTimeFormatter.ISO_TIME),
                date = date.format(DateTimeFormatter.ISO_DATE),
            )

        val RepeatingAlarm.asOneTime
            get() = run {
                OneTimeAlarm(
                    id = id,
                    tag = tag,
                    idle = idle,
                    exact = exact,
                    running = running,
                    message = message,
                    type = type,
                    operation = operation,
                    time = time,
                    date = date,
                )
            }
    }
}
