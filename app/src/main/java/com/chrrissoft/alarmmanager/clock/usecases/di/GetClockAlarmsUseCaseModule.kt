package com.chrrissoft.alarmmanager.clock.usecases.di

import com.chrrissoft.alarmmanager.clock.usecases.classes.GetClockAlarmsUseCaseImpl
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.GetClockAlarmsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GetClockAlarmsUseCaseModule {
    @Binds
    abstract fun binds(impl: GetClockAlarmsUseCaseImpl) : GetClockAlarmsUseCase
}
