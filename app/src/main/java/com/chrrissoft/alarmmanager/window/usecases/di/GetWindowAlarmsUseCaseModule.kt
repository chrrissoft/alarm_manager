package com.chrrissoft.alarmmanager.window.usecases.di

import com.chrrissoft.alarmmanager.window.usecases.classes.GetWindowAlarmsUseCaseImpl
import com.chrrissoft.alarmmanager.window.usecases.interfaces.GetWindowAlarmsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GetWindowAlarmsUseCaseModule {
    @Binds
    abstract fun binds(impl: GetWindowAlarmsUseCaseImpl) : GetWindowAlarmsUseCase
}
