package com.chrrissoft.alarmmanager.repeating.usecases.di

import com.chrrissoft.alarmmanager.repeating.usecases.classes.CancelRepeatingAlarmUseCaseImpl
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.CancelRepeatingAlarmUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CancelRepeatingAlarmUseCaseModule {
    @Binds
    abstract fun binds(impl: CancelRepeatingAlarmUseCaseImpl) : CancelRepeatingAlarmUseCase
}
