package com.chrrissoft.alarmmanager.di

import android.app.AlarmManager.OnAlarmListener
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AlarmListenersModule {
    @Provides
    @Singleton
    fun provide(): MutableMap<String, OnAlarmListener> = mutableMapOf()
}
