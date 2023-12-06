package com.chrrissoft.alarmmanager.repeating.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chrrissoft.alarmmanager.entities.AlarmOperation
import com.chrrissoft.alarmmanager.entities.AlarmType
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter.ISO_DATE
import org.threeten.bp.format.DateTimeFormatter.ISO_TIME
import java.util.UUID
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm as CommonRepeatingAlarm

@Entity(tableName = "repeating_alarms")
data class RepeatingAlarm(
    @PrimaryKey
    @ColumnInfo("id") val id: String = UUID.randomUUID().toString(),
    @ColumnInfo("running") val running: Boolean = false,
    @ColumnInfo("message") val message: String = "",
    @ColumnInfo("type") val type: AlarmType = AlarmType.RTC,
    @ColumnInfo("operation") val operation: AlarmOperation = AlarmOperation.Receiver,
    @ColumnInfo("time") val time: String = LocalTime.now().format(ISO_TIME),
    @ColumnInfo("date") val date: String = LocalDate.now().format(ISO_DATE),
    @ColumnInfo("repeating") val repeating: Long = 10.minutes.inWholeMilliseconds,
) {
    companion object {
        val RepeatingAlarm.asCommon
            get() = run {
                CommonRepeatingAlarm(
                    id = id,
                    message = message,
                    running = running,
                    type = type,
                    operation = operation,
                    time = LocalTime.parse(time, ISO_TIME),
                    date = LocalDate.parse(date, ISO_DATE),
                    repeating = repeating.milliseconds
                )
            }

        val List<RepeatingAlarm>.asCommon get() = map { it.asCommon }
    }
}
