package com.chrrissoft.alarmmanager.tags.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmTagsDao {
    @Query("SELECT * FROM alarm_tags")
    fun get(): Flow<List<AlarmTag>>

    @Query("SELECT * FROM alarm_tags WHERE :id = id")
    fun get(id: String): Flow<AlarmTag>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(vararg users: AlarmTag)

    @Update
    suspend fun update(vararg users: AlarmTag)

    @Delete
    suspend fun delete(vararg users: AlarmTag)
}
