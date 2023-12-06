package com.chrrissoft.alarmmanager.tags.usecases.di

import com.chrrissoft.alarmmanager.tags.usecases.classes.DeleteAlarmTagUseCaseImpl
import com.chrrissoft.alarmmanager.tags.usecases.interfaces.DeleteAlarmTagUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DeleteAlarmTagUseCaseModule {
    @Binds
    abstract fun binds(impl: DeleteAlarmTagUseCaseImpl) : DeleteAlarmTagUseCase
}
