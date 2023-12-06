package com.chrrissoft.alarmmanager.onetime.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OneTimeAlarmDao {
    @Query("SELECT * FROM one_time_alarms")
    fun get(): Flow<List<OneTimeAlarm>>

    @Query("SELECT * FROM one_time_alarms WHERE :id = id")
    fun get(id: String): Flow<OneTimeAlarm>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg users: OneTimeAlarm)

    @Delete
    suspend fun delete(vararg users: OneTimeAlarm)
}
