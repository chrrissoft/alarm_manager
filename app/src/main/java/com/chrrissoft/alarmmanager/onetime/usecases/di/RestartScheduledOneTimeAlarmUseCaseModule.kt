package com.chrrissoft.alarmmanager.onetime.usecases.di

import com.chrrissoft.alarmmanager.onetime.usecases.classes.RestartScheduledOneTimeAlarmUseCaseImpl
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.RestartScheduledOneTimeAlarmUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RestartScheduledOneTimeAlarmUseCaseModule {
    @Binds
    abstract fun binds(impl: RestartScheduledOneTimeAlarmUseCaseImpl) : RestartScheduledOneTimeAlarmUseCase
}
