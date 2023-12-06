package com.chrrissoft.alarmmanager.usecases.di

import com.chrrissoft.alarmmanager.usecases.classes.ScheduleAlarmsOnBootUseCaseImpl
import com.chrrissoft.alarmmanager.usecases.interfaces.ScheduleAlarmsOnBootUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ScheduleAlarmsOnBootUseCaseModule {
    @Binds
    abstract fun binds(impl: ScheduleAlarmsOnBootUseCaseImpl) : ScheduleAlarmsOnBootUseCase
}
