package com.chrrissoft.alarmmanager.clock.usecases.di

import com.chrrissoft.alarmmanager.clock.usecases.classes.CancelClockAlarmUseCaseImpl
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.CancelClockAlarmUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CancelClockAlarmUseCaseModule {
    @Binds
    abstract fun binds(impl: CancelClockAlarmUseCaseImpl) : CancelClockAlarmUseCase
}
