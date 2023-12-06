package com.chrrissoft.alarmmanager.repeating.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RepeatingAlarmDao {
    @Query("SELECT * FROM repeating_alarms")
    fun get(): Flow<List<RepeatingAlarm>>

    @Query("SELECT * FROM repeating_alarms WHERE :id = id")
    fun get(id: String): Flow<RepeatingAlarm>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg users: RepeatingAlarm)

    @Delete
    suspend fun delete(vararg users: RepeatingAlarm)
}
