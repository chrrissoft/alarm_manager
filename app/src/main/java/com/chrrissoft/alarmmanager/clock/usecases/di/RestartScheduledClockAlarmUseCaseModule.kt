package com.chrrissoft.alarmmanager.clock.usecases.di

import com.chrrissoft.alarmmanager.clock.usecases.classes.RestartScheduledClockAlarmUseCaseImpl
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.RestartScheduledClockAlarmUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RestartScheduledClockAlarmUseCaseModule {
    @Binds
    abstract fun binds(impl: RestartScheduledClockAlarmUseCaseImpl) : RestartScheduledClockAlarmUseCase
}
