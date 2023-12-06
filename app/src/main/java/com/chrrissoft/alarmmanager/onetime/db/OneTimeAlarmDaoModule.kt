package com.chrrissoft.alarmmanager.onetime.db

import com.chrrissoft.alarmmanager.app.AlarmManagerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OneTimeAlarmDaoModule {
    @Provides
    @Singleton
    fun provide(database: AlarmManagerDatabase): OneTimeAlarmDao {
        return database.oneTimeDao
    }
}
