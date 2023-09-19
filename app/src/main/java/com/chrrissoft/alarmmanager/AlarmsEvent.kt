package com.chrrissoft.alarmmanager

import com.chrrissoft.alarmmanager.AlarmsState.*
import com.chrrissoft.alarmmanager.AlarmsViewModel.EventHandler
import com.chrrissoft.alarmmanager.AlarmsViewModel.EventHandler.*
import com.chrrissoft.alarmmanager.base.AlarmBuilder.*

sealed interface AlarmsEvent {
    fun resolve(handler: EventHandler) {
        when (this) {
            is TagAlarmsEvent -> resolve(handler.tagsHandler)
            is OneTimeAlarmsEvent -> resolve(handler.oneTimeHandler)
            is OneTimeIdleAlarmsEvent -> resolve(handler.oneTimeIdleHandler)
            is WindowsAlarmsEvent -> resolve(handler.windowsHandler)
            is RepeatingAlarmsEvent -> resolve(handler.repeatingHandler)
        }
    }

    sealed interface TagAlarmsEvent : AlarmsEvent {
        fun resolve(handler: TagAlarmsEventHandler) {
            when (this) {
                is OnChangeTags -> handler.onEvents(event = this)
            }
        }

        data class OnChangeTags(val data: Set<String>) : TagAlarmsEvent
    }

    sealed interface OneTimeAlarmsEvent : AlarmsEvent {
        fun resolve(handler: OneTimeAlarmsEventHandler) {
            when (this) {
                is OnChangeState -> handler.onEvent(event = this)
                is OnLaunch -> handler.onEvent(event = this)
                is OnCancel -> handler.onEvent(event = this)
                is OnCreate -> handler.onEvent(event = this)
            }
        }

        data class OnCreate(val data: OneTimeBuilder) : OneTimeAlarmsEvent

        data class OnChangeState(val data: OneTimeState) : OneTimeAlarmsEvent

        data class OnLaunch(val data: OneTimeBuilder) : OneTimeAlarmsEvent

        data class OnCancel(val data: OneTimeBuilder) : OneTimeAlarmsEvent
    }

    sealed interface OneTimeIdleAlarmsEvent : AlarmsEvent {
        fun resolve(handler: OneTimeIdleAlarmsEventHandler) {
            when (this) {
                is OnChangeState -> handler.onEvent(event = this)
                is OnLaunch -> handler.onEvent(event = this)
                is OnCancel -> handler.onEvent(event = this)
                is OnCreate -> handler.onEvent(event = this)
            }
        }

        data class OnCreate(val data: OneTimeIdleBuilder) : OneTimeIdleAlarmsEvent

        data class OnChangeState(val data: OneTimeIdleState) : OneTimeIdleAlarmsEvent

        data class OnLaunch(val data: OneTimeIdleBuilder) : OneTimeIdleAlarmsEvent

        data class OnCancel(val data: OneTimeIdleBuilder) : OneTimeIdleAlarmsEvent
    }

    sealed interface WindowsAlarmsEvent : AlarmsEvent {
        fun resolve(handler: WindowsAlarmsEventHandler) {
            when (this) {
                is OnChangeState -> handler.onEvent(event = this)
                is OnLaunch -> handler.onEvent(event = this)
                is OnCancel -> handler.onEvent(event = this)
                is OnCreate -> handler.onEvent(event = this)
            }
        }

        data class OnCreate(val data: WindowsBuilder) : WindowsAlarmsEvent

        data class OnChangeState(val data: WindowsAlarmsState) : WindowsAlarmsEvent

        data class OnLaunch(val data: WindowsBuilder) : WindowsAlarmsEvent

        data class OnCancel(val data: WindowsBuilder) : WindowsAlarmsEvent
    }

    sealed interface RepeatingAlarmsEvent : AlarmsEvent {
        fun resolve(handler: RepeatingAlarmsEventHandler) {
            when (this) {
                is OnChangeState -> handler.onEvent(event = this)
                is OnLaunch -> handler.onEvent(event = this)
                is OnCancel -> handler.onEvent(event = this)
                is OnCreate -> handler.onEvent(event = this)
            }
        }

        data class OnCreate(val data: RepeatingBuilder) : RepeatingAlarmsEvent

        data class OnChangeState(val data: RepeatingAlarmsState) : RepeatingAlarmsEvent

        data class OnLaunch(val data: RepeatingBuilder) : RepeatingAlarmsEvent

        data class OnCancel(val data: RepeatingBuilder) : RepeatingAlarmsEvent
    }
}
