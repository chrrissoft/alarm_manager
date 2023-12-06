package com.chrrissoft.alarmmanager.onetime.entities

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
import com.chrrissoft.alarmmanager.onetime.db.OneTimeAlarm as OneTimeAlarmDb

@Serializable
data class OneTimeAlarm(
    override val id: String = UUID.randomUUID().toString(),
    val tag: String? = null,
    val idle: Boolean = true,
    val exact: Boolean = false,
    val running: Boolean = false,
    override val message: String = "",
    @SerialName("alarm_type") val type: AlarmType = AlarmType.RTC,
    override val operation: AlarmOperation = AlarmOperation.Receiver,
    @Contextual @Serializable(with = LocalTimeSerializer::class)  val time: LocalTime = LocalTime.now(),
    @Contextual @Serializable(with = LocalDateSerializer::class) val date: LocalDate = LocalDate.now(),
) : Alarm {
    val triggerTime = LocalDateTime.of(date, time).getMillis()
    companion object {

        val OneTimeAlarm.asDb get() = OneTimeAlarmDb(
            id = id,
            idle = idle,
            exact = exact,
            running = running,
            tag = tag,
            type = type,
            message = message,
            operation = operation,
            time = time.format(DateTimeFormatter.ISO_TIME),
            date = date.format(DateTimeFormatter.ISO_DATE),
        )
    }
}
