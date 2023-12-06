package com.chrrissoft.alarmmanager.repeating.usecases.di

import com.chrrissoft.alarmmanager.repeating.usecases.classes.ScheduleRepeatingAlarmsUseCaseImpl
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.ScheduleRepeatingAlarmsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ScheduleRepeatingUseCaseModule {
    @Binds
    abstract fun binds(impl: ScheduleRepeatingAlarmsUseCaseImpl) : ScheduleRepeatingAlarmsUseCase
}
