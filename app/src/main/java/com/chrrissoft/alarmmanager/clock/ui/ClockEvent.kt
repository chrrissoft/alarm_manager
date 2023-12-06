package com.chrrissoft.alarmmanager.clock.ui

import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.clock.ui.ClockViewModel.EvenHandler
import com.chrrissoft.alarmmanager.clock.ui.ClockViewModel.EvenHandler.ListingEventHandler
import com.chrrissoft.alarmmanager.entities.ListDetailPage

sealed interface ClockEvent {
    fun resolve(handler: EvenHandler) {
        when (this) {
            is ClockBuilderEvent -> resolve(handler.builderHandler)
            is ClockListingEvent -> resolve(handler.listingHandler)
            is OnChangePage -> handler.handleEvent(event = this)
        }
    }

    sealed interface ClockBuilderEvent : ClockEvent {
        fun resolve(handler: EvenHandler.BuilderEventHandler) {
            when (this) {
                is OnChangeBuilder -> handler.handleEvent(event = this)
                is OnSaveBuilder -> handler.handleEvent(event = this)
            }
        }

        data class OnSaveBuilder(val data: List<ClockAlarm>) : ClockBuilderEvent {
            constructor(data: ClockAlarm) : this(listOf(data))
        }

        data class OnChangeBuilder(val data: ClockAlarm) : ClockBuilderEvent
    }

    sealed interface ClockListingEvent : ClockEvent {
        fun resolve(handler: ListingEventHandler) {
            when (this) {
                is OnOpenAlarm -> handler.handleEvent(event = this)
                is OnCancelAlarm -> handler.handleEvent(event = this)
                is OnScheduleAlarm -> handler.handleEvent(event = this)
                is OnDeleteAlarm -> handler.handleEvent(event = this)
            }
        }

        data class OnOpenAlarm(val data: ClockAlarm) : ClockListingEvent

        data class OnCancelAlarm(val data: List<ClockAlarm>) : ClockListingEvent {
            constructor(data: ClockAlarm) : this(listOf(data))
        }

        data class OnDeleteAlarm(val data: List<ClockAlarm>) : ClockListingEvent {
            constructor(data: ClockAlarm) : this(listOf(data))
        }

        data class OnScheduleAlarm(val data: List<ClockAlarm>) : ClockListingEvent {
            constructor(data: ClockAlarm) : this(listOf(data))
        }
    }

    data class OnChangePage(val data: ListDetailPage) : ClockEvent
}
