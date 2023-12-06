package com.chrrissoft.alarmmanager.clock.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ClockAlarmDao {
    @Query("SELECT * FROM clock_alarms")
    fun get(): Flow<List<ClockAlarm>>

    @Query("SELECT * FROM clock_alarms WHERE :id = id")
    fun get(id: String): Flow<ClockAlarm>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg users: ClockAlarm)

    @Delete
    suspend fun delete(vararg users: ClockAlarm)
}
