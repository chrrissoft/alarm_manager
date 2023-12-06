package com.chrrissoft.alarmmanager.clock.usecases.di

import com.chrrissoft.alarmmanager.clock.usecases.classes.SaveClockAlarmUseCaseImpl
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.SaveClockAlarmUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SaveClockAlarmUseCaseModule {
    @Binds
    abstract fun binds(impl: SaveClockAlarmUseCaseImpl) : SaveClockAlarmUseCase
}
