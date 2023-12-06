package com.chrrissoft.alarmmanager.window.usecases.di

import com.chrrissoft.alarmmanager.window.usecases.classes.SaveWindowAlarmUseCaseImpl
import com.chrrissoft.alarmmanager.window.usecases.interfaces.SaveWindowAlarmUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SaveWindowAlarmUseCaseModule {
    @Binds
    abstract fun binds(impl: SaveWindowAlarmUseCaseImpl) : SaveWindowAlarmUseCase
}
