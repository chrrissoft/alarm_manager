package com.chrrissoft.alarmmanager.window.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WindowAlarmDao {
    @Query("SELECT * FROM window_alarms")
    fun get(): Flow<List<WindowAlarm>>

    @Query("SELECT * FROM window_alarms WHERE :id = id")
    fun get(id: String): Flow<WindowAlarm>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg users: WindowAlarm)

    @Update
    suspend fun update(vararg users: WindowAlarm)

    @Delete
    suspend fun delete(vararg users: WindowAlarm)
}
