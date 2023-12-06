package com.chrrissoft.alarmmanager.tags.db

import com.chrrissoft.alarmmanager.app.AlarmManagerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AlarmsTagsDaoModule {
    @Provides
    @Singleton
    fun provide(database: AlarmManagerDatabase): AlarmTagsDao {
        return database.alarmsTagsDao
    }
}
