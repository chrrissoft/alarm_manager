package com.chrrissoft.alarmmanager.di

import android.content.pm.PackageManager
import com.chrrissoft.alarmmanager.AlarmManagerApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PackagerModule {
    @Provides
    fun provide(app: AlarmManagerApp): PackageManager = app.packageManager
}
