package com.chrrissoft.alarmmanager.window.usecases.di

import com.chrrissoft.alarmmanager.window.usecases.classes.RestartScheduledWindowAlarmsUseCaseImpl
import com.chrrissoft.alarmmanager.window.usecases.interfaces.RestartScheduledWindowAlarmsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RestartScheduledWindowUseCaseModule {
    @Binds
    abstract fun binds(impl: RestartScheduledWindowAlarmsUseCaseImpl) : RestartScheduledWindowAlarmsUseCase
}
