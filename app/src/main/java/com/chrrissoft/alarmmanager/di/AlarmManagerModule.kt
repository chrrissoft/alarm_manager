package com.chrrissoft.alarmmanager.di

import android.app.AlarmManager
import androidx.core.content.getSystemService
import com.chrrissoft.alarmmanager.AlarmManagerApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AlarmManagerModule {
    @Provides
    fun provide(app: AlarmManagerApp): AlarmManager = app.getSystemService()!!
}
