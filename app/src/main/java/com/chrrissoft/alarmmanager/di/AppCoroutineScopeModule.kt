package com.chrrissoft.alarmmanager.di

import com.chrrissoft.alarmmanager.AlarmManagerApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object AppCoroutineScopeModule {
    @Provides
    fun provide(app: AlarmManagerApp): CoroutineScope {
        return app.scope
    }
}
