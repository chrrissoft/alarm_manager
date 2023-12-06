package com.chrrissoft.alarmmanager.onetime.usecases.di

import com.chrrissoft.alarmmanager.onetime.usecases.classes.SaveOneTimeAlarmUseCaseImpl
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.SaveOneTimeAlarmUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SaveOneTimeAlarmUseCaseModule {
    @Binds
    abstract fun binds(impl: SaveOneTimeAlarmUseCaseImpl) : SaveOneTimeAlarmUseCase
}
