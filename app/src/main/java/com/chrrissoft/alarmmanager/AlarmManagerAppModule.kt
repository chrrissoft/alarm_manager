package com.chrrissoft.alarmmanager

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AlarmManagerAppModule {
    @Provides
    fun provide(@ApplicationContext ctx: Context): AlarmManagerApp {
        return ctx as AlarmManagerApp
    }
}
