package com.chrrissoft.alarmmanager.usecases.di

import com.chrrissoft.alarmmanager.usecases.classes.DeleteCompleteAlarmUseCaseImpl
import com.chrrissoft.alarmmanager.usecases.interfaces.DeleteCompleteAlarmUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DeleteCompleteAlarmUseCaseModule {
    @Binds
    abstract fun binds(impl: DeleteCompleteAlarmUseCaseImpl) : DeleteCompleteAlarmUseCase
}
