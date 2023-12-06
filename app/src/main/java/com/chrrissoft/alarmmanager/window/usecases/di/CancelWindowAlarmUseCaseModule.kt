package com.chrrissoft.alarmmanager.window.usecases.di

import com.chrrissoft.alarmmanager.window.usecases.classes.CancelWindowAlarmUseCaseImpl
import com.chrrissoft.alarmmanager.window.usecases.interfaces.CancelWindowAlarmUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CancelWindowAlarmUseCaseModule {
    @Binds
    abstract fun binds(impl: CancelWindowAlarmUseCaseImpl) : CancelWindowAlarmUseCase
}
