package com.chrrissoft.alarmmanager.clock.db

import com.chrrissoft.alarmmanager.app.AlarmManagerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClockAlarmDaoModule {
    @Provides
    @Singleton
    fun provide(database: AlarmManagerDatabase): ClockAlarmDao {
        return database.clockAlarms
    }
}
