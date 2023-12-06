package com.chrrissoft.alarmmanager.clock.usecases.di

import com.chrrissoft.alarmmanager.clock.usecases.classes.DeleteClockAlarmUseCaseImpl
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.DeleteClockAlarmUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DeleteClockAlarmUseCaseModule {
    @Binds
    abstract fun binds(impl: DeleteClockAlarmUseCaseImpl) : DeleteClockAlarmUseCase
}
