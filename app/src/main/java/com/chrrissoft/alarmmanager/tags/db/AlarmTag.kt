package com.chrrissoft.alarmmanager.tags.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarm_tags")
data class AlarmTag(
    @PrimaryKey
    @ColumnInfo("id") val id: String,
)
