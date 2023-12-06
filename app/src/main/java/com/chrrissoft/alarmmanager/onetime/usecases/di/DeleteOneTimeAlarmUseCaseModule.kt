package com.chrrissoft.alarmmanager.onetime.usecases.di

import com.chrrissoft.alarmmanager.onetime.usecases.classes.DeleteOneTimeAlarmUseCaseImpl
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.DeleteOneTimeAlarmUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DeleteOneTimeAlarmUseCaseModule {
    @Binds
    abstract fun binds(impl: DeleteOneTimeAlarmUseCaseImpl) : DeleteOneTimeAlarmUseCase
}
