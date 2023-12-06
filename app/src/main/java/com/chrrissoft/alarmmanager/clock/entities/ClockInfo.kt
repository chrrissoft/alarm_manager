package com.chrrissoft.alarmmanager.clock.entities

import com.chrrissoft.alarmmanager.entities.AlarmOperation
import com.chrrissoft.alarmmanager.settings.LocalDateSerializer
import com.chrrissoft.alarmmanager.settings.LocalTimeSerializer
import com.chrrissoft.alarmmanager.utils.DateTimeUtils.getMillis
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime

@Serializable
data class ClockInfo(
    @Contextual @Serializable(with = LocalTimeSerializer::class) val time: LocalTime = LocalTime.now(),
    @Contextual @Serializable(with = LocalDateSerializer::class) val date: LocalDate = LocalDate.now(),
    val component: AlarmOperation = AlarmOperation.Activity,
) {
    val triggerTime = LocalDateTime.of(date, time).getMillis()
}
