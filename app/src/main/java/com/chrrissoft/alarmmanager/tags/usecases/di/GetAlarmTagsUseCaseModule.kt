package com.chrrissoft.alarmmanager.tags.usecases.di

import com.chrrissoft.alarmmanager.tags.usecases.classes.GetAlarmTagsUseCaseImpl
import com.chrrissoft.alarmmanager.tags.usecases.interfaces.GetAlarmTagsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GetAlarmTagsUseCaseModule {
    @Binds
    abstract fun binds(impl: GetAlarmTagsUseCaseImpl) : GetAlarmTagsUseCase
}
