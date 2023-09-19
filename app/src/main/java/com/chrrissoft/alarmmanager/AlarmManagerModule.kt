package com.chrrissoft.alarmmanager

import android.app.AlarmManager
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AlarmManagerModule {
    @Provides
    fun provide(@ApplicationContext app: Context): AlarmManager {
        return app.getSystemService(AlarmManager::class.java)
    }
}
