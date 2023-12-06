package com.chrrissoft.alarmmanager.window.usecases.di

import com.chrrissoft.alarmmanager.window.usecases.classes.DeleteWindowAlarmUseCaseImpl
import com.chrrissoft.alarmmanager.window.usecases.interfaces.DeleteWindowAlarmUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DeleteWindowAlarmUseCaseModule {
    @Binds
    abstract fun binds(impl: DeleteWindowAlarmUseCaseImpl) : DeleteWindowAlarmUseCase
}
