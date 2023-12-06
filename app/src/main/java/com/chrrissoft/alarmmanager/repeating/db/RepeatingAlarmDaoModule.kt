package com.chrrissoft.alarmmanager.repeating.db

import com.chrrissoft.alarmmanager.app.AlarmManagerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepeatingAlarmDaoModule {
    @Provides
    @Singleton
    fun provide(database: AlarmManagerDatabase): RepeatingAlarmDao {
        return database.repeatingDao
    }
}
