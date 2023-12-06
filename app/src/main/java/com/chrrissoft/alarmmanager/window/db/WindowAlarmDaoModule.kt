package com.chrrissoft.alarmmanager.window.db

import com.chrrissoft.alarmmanager.app.AlarmManagerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WindowAlarmDaoModule {
    @Provides
    @Singleton
    fun provide(database: AlarmManagerDatabase): WindowAlarmDao {
        return database.windowDao
    }
}
