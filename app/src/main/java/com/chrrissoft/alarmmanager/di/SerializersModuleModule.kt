package com.chrrissoft.alarmmanager.di

import com.chrrissoft.alarmmanager.base.Alarm
import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Module
@InstallIn(SingletonComponent::class)
object SerializersModuleModule {
    @Provides
    fun provide(): SerializersModule {
        return SerializersModule {
            polymorphic(Alarm::class) {
                subclass(OneTimeAlarm::class)
                subclass(RepeatingAlarm::class)
                subclass(WindowAlarm::class)
                subclass(ClockAlarm::class)
            }
        }
    }
}
