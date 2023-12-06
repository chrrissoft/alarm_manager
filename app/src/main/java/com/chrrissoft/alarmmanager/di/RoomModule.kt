package com.chrrissoft.alarmmanager.di

import androidx.room.Room
import com.chrrissoft.alarmmanager.AlarmManagerApp
import com.chrrissoft.alarmmanager.Constants
import com.chrrissoft.alarmmanager.app.AlarmManagerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    fun provide(app: AlarmManagerApp) : AlarmManagerDatabase {
        return Room.databaseBuilder(
            context = app,
            name = Constants.DATABASE_NAME,
            klass = AlarmManagerDatabase::class.java,
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
