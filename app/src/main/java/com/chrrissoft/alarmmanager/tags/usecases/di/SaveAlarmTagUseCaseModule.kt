package com.chrrissoft.alarmmanager.tags.usecases.di

import com.chrrissoft.alarmmanager.tags.usecases.classes.SaveAlarmTagUseCaseImpl
import com.chrrissoft.alarmmanager.tags.usecases.interfaces.SaveAlarmTagUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SaveAlarmTagUseCaseModule {
    @Binds
    abstract fun binds(impl: SaveAlarmTagUseCaseImpl) : SaveAlarmTagUseCase
}
