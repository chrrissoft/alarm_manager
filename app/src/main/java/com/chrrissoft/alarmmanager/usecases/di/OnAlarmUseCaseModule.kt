package com.chrrissoft.alarmmanager.usecases.di

import com.chrrissoft.alarmmanager.usecases.classes.OnAlarmUseCaseImpl
import com.chrrissoft.alarmmanager.usecases.interfaces.OnAlarmUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class OnAlarmUseCaseModule {
    @Binds
    abstract fun binds(impl: OnAlarmUseCaseImpl) : OnAlarmUseCase
}
