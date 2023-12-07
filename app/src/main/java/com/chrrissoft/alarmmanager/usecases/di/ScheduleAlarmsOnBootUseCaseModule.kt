package com.chrrissoft.alarmmanager.usecases.di

import com.chrrissoft.alarmmanager.usecases.classes.RestartScheduleAlarmsUseCaseImpl
import com.chrrissoft.alarmmanager.usecases.interfaces.RestartScheduleAlarmsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ScheduleAlarmsOnBootUseCaseModule {
    @Binds
    abstract fun binds(impl: RestartScheduleAlarmsUseCaseImpl) : RestartScheduleAlarmsUseCase
}
