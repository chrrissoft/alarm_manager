package com.chrrissoft.alarmmanager.clock.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chrrissoft.alarmmanager.clock.entities.ClockInfo
import com.chrrissoft.alarmmanager.entities.AlarmOperation
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter.ISO_DATE
import org.threeten.bp.format.DateTimeFormatter.ISO_TIME
import java.util.UUID
import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm as CommonClockAlarm

@Entity(tableName = "clock_alarms")
data class ClockAlarm(
    @PrimaryKey
    @ColumnInfo("id") val id: String = UUID.randomUUID().toString(),
    @ColumnInfo("running") val running: Boolean = false,
    @ColumnInfo("message") val message: String = "",
    @ColumnInfo("operation") val operation: AlarmOperation = AlarmOperation.Receiver,
    @ColumnInfo("component") val component: AlarmOperation = AlarmOperation.Receiver,
    @ColumnInfo("time") val time: String = LocalTime.now().format(ISO_TIME),
    @ColumnInfo("date") val date: String = LocalDate.now().format(ISO_DATE),
) {
    companion object {
        val ClockAlarm.asCommon
            get() = run {
                CommonClockAlarm(
                    id = id,
                    message = message,
                    clockInfo = ClockInfo(
                        time = LocalTime.parse(time, ISO_TIME),
                        date = LocalDate.parse(date, ISO_DATE),
                        component = component,
                    ),
                    running = running,
                    operation = operation,
                )
            }

        val List<ClockAlarm>.asCommon get() = map { it.asCommon }
    }
}
