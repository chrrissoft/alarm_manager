package com.chrrissoft.alarmmanager.utils

import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

object DateTimeUtils {
    fun Long.toLocalDate(zoneId: ZoneId = ZoneId.systemDefault()): LocalDate {
        val instant = Instant.ofEpochMilli(this)
        val zonedDateTime = instant.atZone(zoneId)
        return zonedDateTime.toLocalDate()
    }

    fun LocalDateTime.getMillis(zoneId: ZoneId = ZoneId.systemDefault()): Long {
        return Instant.from(this.atZone(zoneId)).toEpochMilli()
    }
}