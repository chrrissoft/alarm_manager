package com.chrrissoft.alarmmanager.app

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chrrissoft.alarmmanager.clock.db.ClockAlarm
import com.chrrissoft.alarmmanager.clock.db.ClockAlarmDao
import com.chrrissoft.alarmmanager.onetime.db.OneTimeAlarm
import com.chrrissoft.alarmmanager.onetime.db.OneTimeAlarmDao
import com.chrrissoft.alarmmanager.repeating.db.RepeatingAlarm
import com.chrrissoft.alarmmanager.repeating.db.RepeatingAlarmDao
import com.chrrissoft.alarmmanager.tags.db.AlarmTag
import com.chrrissoft.alarmmanager.tags.db.AlarmTagsDao
import com.chrrissoft.alarmmanager.window.db.WindowAlarm
import com.chrrissoft.alarmmanager.window.db.WindowAlarmDao

@Database(
    entities = [
        OneTimeAlarm::class,
        WindowAlarm::class,
        RepeatingAlarm::class,
        AlarmTag::class,
        ClockAlarm::class,
    ],
    version = 7
)
abstract class AlarmManagerDatabase : RoomDatabase() {
    abstract val clockAlarms: ClockAlarmDao
    abstract val alarmsTagsDao: AlarmTagsDao
    abstract val windowDao: WindowAlarmDao
    abstract val oneTimeDao: OneTimeAlarmDao
    abstract val repeatingDao: RepeatingAlarmDao
}
