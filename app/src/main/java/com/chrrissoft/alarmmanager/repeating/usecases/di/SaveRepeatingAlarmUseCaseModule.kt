package com.chrrissoft.alarmmanager.repeating.usecases.di

import com.chrrissoft.alarmmanager.repeating.usecases.classes.SaveRepeatingAlarmUseCaseImpl
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.SaveRepeatingAlarmUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SaveRepeatingAlarmUseCaseModule {
    @Binds
    abstract fun binds(impl: SaveRepeatingAlarmUseCaseImpl) : SaveRepeatingAlarmUseCase
}
