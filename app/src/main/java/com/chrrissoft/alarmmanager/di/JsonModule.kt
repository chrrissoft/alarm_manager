package com.chrrissoft.alarmmanager.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

@Module
@InstallIn(SingletonComponent::class)
object JsonModule {
    @Provides
    fun provide(modules: SerializersModule) : Json = Json { serializersModule = modules }
}
