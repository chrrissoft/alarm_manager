package com.chrrissoft.alarmmanager.onetime.ui

import com.chrrissoft.alarmmanager.entities.ListDetailPage
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
import com.chrrissoft.alarmmanager.onetime.ui.OneTimeViewModel.EvenHandler
import com.chrrissoft.alarmmanager.onetime.ui.OneTimeViewModel.EvenHandler.ListingEventHandler

sealed interface OneTimeEvent {
    fun resolve(handler: EvenHandler) {
        when (this) {
            is OneTimeBuilderEvent -> resolve(handler.builderHandler)
            is OneTimeListingEvent -> resolve(handler.listingHandler)
            is OnChangePage -> handler.handleEvent(event = this)
        }
    }

    sealed interface OneTimeBuilderEvent : OneTimeEvent {
        fun resolve(handler: EvenHandler.BuilderEventHandler) {
            when (this) {
                is OnChangeBuilder -> handler.handleEvent(event = this)
                is OnSaveBuilder -> handler.handleEvent(event = this)
            }
        }

        data class OnSaveBuilder(val data: List<OneTimeAlarm>) : OneTimeBuilderEvent {
            constructor(data: OneTimeAlarm) : this(listOf(data))
        }

        data class OnChangeBuilder(val data: OneTimeAlarm) : OneTimeBuilderEvent
    }

    sealed interface OneTimeListingEvent : OneTimeEvent {
        fun resolve(handler: ListingEventHandler) {
            when (this) {
                is OnOpenAlarm -> handler.handleEvent(event = this)
                is OnCancelAlarm -> handler.handleEvent(event = this)
                is OnScheduleAlarm -> handler.handleEvent(event = this)
                is OnDeleteAlarm -> handler.handleEvent(event = this)
            }
        }

        data class OnOpenAlarm(val data: OneTimeAlarm) : OneTimeListingEvent

        data class OnCancelAlarm(val data: List<OneTimeAlarm>) : OneTimeListingEvent {
            constructor(data: OneTimeAlarm) : this(listOf(data))
        }

        data class OnDeleteAlarm(val data: List<OneTimeAlarm>) : OneTimeListingEvent {
            constructor(data: OneTimeAlarm) : this(listOf(data))
        }

        data class OnScheduleAlarm(val data: List<OneTimeAlarm>) : OneTimeListingEvent {
            constructor(data: OneTimeAlarm) : this(listOf(data))
        }
    }

    data class OnChangePage(val data: ListDetailPage) : OneTimeEvent
}
