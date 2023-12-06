package com.chrrissoft.alarmmanager.clock.entities

import com.chrrissoft.alarmmanager.base.Alarm
import com.chrrissoft.alarmmanager.entities.AlarmOperation
import kotlinx.serialization.Serializable
import org.threeten.bp.format.DateTimeFormatter
import java.util.UUID
import com.chrrissoft.alarmmanager.clock.db.ClockAlarm as ClockAlarmDb

@Serializable
data class ClockAlarm(
    override val id: String = UUID.randomUUID().toString(),
    val running: Boolean = false,
    val clockInfo: ClockInfo = ClockInfo(),
    override val operation: AlarmOperation = AlarmOperation.Receiver,
    override val message: String = ""
) : Alarm {
    companion object {
        val ClockAlarm.asDb get() = ClockAlarmDb(
            id = id,
            running = running,
            component = clockInfo.component,
            operation = operation,
            message = message,
            time = clockInfo.time.format(DateTimeFormatter.ISO_TIME),
            date = clockInfo.date.format(DateTimeFormatter.ISO_DATE),
        )
    }
}
