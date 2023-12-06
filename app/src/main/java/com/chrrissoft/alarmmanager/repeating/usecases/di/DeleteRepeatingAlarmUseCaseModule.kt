package com.chrrissoft.alarmmanager.repeating.usecases.di

import com.chrrissoft.alarmmanager.repeating.usecases.classes.DeleteRepeatingAlarmUseCaseImpl
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.DeleteRepeatingAlarmUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DeleteRepeatingAlarmUseCaseModule {
    @Binds
    abstract fun binds(impl: DeleteRepeatingAlarmUseCaseImpl) : DeleteRepeatingAlarmUseCase
}
