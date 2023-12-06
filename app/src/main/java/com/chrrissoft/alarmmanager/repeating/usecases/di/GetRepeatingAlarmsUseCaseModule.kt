package com.chrrissoft.alarmmanager.repeating.usecases.di

import com.chrrissoft.alarmmanager.repeating.usecases.classes.GetRepeatingAlarmsUseCaseImpl
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.GetRepeatingAlarmsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GetRepeatingAlarmsUseCaseModule {
    @Binds
    abstract fun binds(impl: GetRepeatingAlarmsUseCaseImpl) : GetRepeatingAlarmsUseCase
}
