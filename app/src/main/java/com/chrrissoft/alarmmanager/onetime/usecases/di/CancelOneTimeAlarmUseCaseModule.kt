package com.chrrissoft.alarmmanager.onetime.usecases.di

import com.chrrissoft.alarmmanager.onetime.usecases.classes.CancelOneTimeAlarmUseCaseImpl
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.CancelOneTimeAlarmUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CancelOneTimeAlarmUseCaseModule {
    @Binds
    abstract fun binds(impl: CancelOneTimeAlarmUseCaseImpl) : CancelOneTimeAlarmUseCase
}
