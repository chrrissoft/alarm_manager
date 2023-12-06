package com.chrrissoft.alarmmanager.onetime.usecases.di

import com.chrrissoft.alarmmanager.onetime.usecases.classes.GetOneTimeAlarmsUseCaseImpl
import com.chrrissoft.alarmmanager.onetime.usecases.interfaces.GetOneTimeAlarmsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GetOneTimeAlarmsUseCaseModule {
    @Binds
    abstract fun binds(impl: GetOneTimeAlarmsUseCaseImpl) : GetOneTimeAlarmsUseCase
}
