package com.chrrissoft.alarmmanager.window.db

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
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm as CommonWindowAlarm

@Entity(tableName = "window_alarms")
data class WindowAlarm(
    @PrimaryKey
    @ColumnInfo("id") val id: String = UUID.randomUUID().toString(),
    @ColumnInfo("message") val message: String = "",
    @ColumnInfo("running") val running: Boolean = false,
    @ColumnInfo("tag") val tag: String? = null,
    @ColumnInfo("type") val type: AlarmType = AlarmType.RTC,
    @ColumnInfo("operation") val operation: AlarmOperation = AlarmOperation.Receiver,
    @ColumnInfo("time") val time: String = LocalTime.now().format(ISO_TIME),
    @ColumnInfo("date") val date: String = LocalDate.now().format(ISO_DATE),
    @ColumnInfo("window") val window: Long = 10.minutes.inWholeMilliseconds,
) {
    companion object {
        val WindowAlarm.asCommon
            get() = run {
                CommonWindowAlarm(
                    id = id,
                    running = running,
                    message = message,
                    tag = tag,
                    type = type,
                    operation = operation,
                    time = LocalTime.parse(time, ISO_TIME),
                    date = LocalDate.parse(date, ISO_DATE),
                    window = window.milliseconds
                )
            }

        val List<WindowAlarm>.asCommon get() = map { it.asCommon }
    }
}
