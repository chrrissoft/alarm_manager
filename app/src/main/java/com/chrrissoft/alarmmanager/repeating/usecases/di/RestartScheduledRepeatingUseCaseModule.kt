package com.chrrissoft.alarmmanager.repeating.usecases.di

import com.chrrissoft.alarmmanager.repeating.usecases.classes.RestartScheduledRepeatingAlarmsUseCaseImpl
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.RestartScheduledRepeatingAlarmsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RestartScheduledRepeatingUseCaseModule {
    @Binds
    abstract fun binds(impl: RestartScheduledRepeatingAlarmsUseCaseImpl) : RestartScheduledRepeatingAlarmsUseCase
}
