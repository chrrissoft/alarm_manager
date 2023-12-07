package com.chrrissoft.alarmmanager.usecases.di

import com.chrrissoft.alarmmanager.usecases.classes.EnableBootReceiversOnDemandUseCaseImpl
import com.chrrissoft.alarmmanager.usecases.interfaces.EnableBootReceiversOnDemandUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class EnableBootReceiversOnDemandUseCaseModule {
    @Binds
    abstract fun binds(impl: EnableBootReceiversOnDemandUseCaseImpl) : EnableBootReceiversOnDemandUseCase
}
