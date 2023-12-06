package com.chrrissoft.alarmmanager.window.usecases.di

import com.chrrissoft.alarmmanager.window.usecases.classes.ScheduleWindowAlarmsUseCaseImpl
import com.chrrissoft.alarmmanager.window.usecases.interfaces.ScheduleWindowAlarmsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ScheduleWindowUseCaseModule {
    @Binds
    abstract fun binds(impl: ScheduleWindowAlarmsUseCaseImpl) : ScheduleWindowAlarmsUseCase
}
