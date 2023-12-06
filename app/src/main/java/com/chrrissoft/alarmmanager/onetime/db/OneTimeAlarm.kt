package com.chrrissoft.alarmmanager.onetime.db

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
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm as CommonOneTimeAlarm

@Entity(tableName = "one_time_alarms")
data class OneTimeAlarm(
    @PrimaryKey
    @ColumnInfo("id") val id: String = UUID.randomUUID().toString(),
    @ColumnInfo("idle") val idle: Boolean = true,
    @ColumnInfo("exact") val exact: Boolean = false,
    @ColumnInfo("message") val message: String = "",
    @ColumnInfo("running") val running: Boolean = false,
    @ColumnInfo("tag") val tag: String? = null,
    @ColumnInfo("type") val type: AlarmType = AlarmType.RTC,
    @ColumnInfo("operation") val operation: AlarmOperation = AlarmOperation.Receiver,
    @ColumnInfo("time") val time: String = LocalTime.now().format(ISO_TIME),
    @ColumnInfo("date") val date: String = LocalDate.now().format(ISO_DATE),
) {
    companion object {
        val OneTimeAlarm.asCommon
            get() = run {
                    CommonOneTimeAlarm(
                        id = id,
                        idle = idle,
                        exact = exact,
                        running = running,
                        tag = tag,
                        type = type,
                        operation = operation,
                        message = message,
                        time = LocalTime.parse(time, ISO_TIME),
                        date = LocalDate.parse(date, ISO_DATE),
                    )
                }

        val List<OneTimeAlarm>.asCommon get() = map { it.asCommon }
    }
}
