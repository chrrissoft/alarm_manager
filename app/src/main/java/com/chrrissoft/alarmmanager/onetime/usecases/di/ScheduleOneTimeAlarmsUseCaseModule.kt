package com.chrrissoft.alarmmanager.onetime.usecases.di

import com.chrrissoft.alarmmanager.onetime.usecases.classes.ScheduleOneTimeAlarmsUseCaseImpl
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.ScheduleOneTimeAlarmsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ScheduleOneTimeAlarmsUseCaseModule {
    @Binds
    abstract fun binds(impl: ScheduleOneTimeAlarmsUseCaseImpl) : ScheduleOneTimeAlarmsUseCase
}
