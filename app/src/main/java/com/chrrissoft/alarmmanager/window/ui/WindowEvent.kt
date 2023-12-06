package com.chrrissoft.alarmmanager.window.ui

import com.chrrissoft.alarmmanager.entities.ListDetailPage
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm
import com.chrrissoft.alarmmanager.window.ui.WindowViewModel.EvenHandler
import com.chrrissoft.alarmmanager.window.ui.WindowViewModel.EvenHandler.ListingEventHandler

sealed interface WindowEvent {
    fun resolve(handler: EvenHandler) {
        when (this) {
            is WindowBuilderEvent -> resolve(handler.builderHandler)
            is WindowListingEvent -> resolve(handler.listingHandler)
            is OnChangePage -> handler.handleEvent(event = this)
        }
    }

    sealed interface WindowBuilderEvent : WindowEvent {
        fun resolve(handler: EvenHandler.BuilderEventHandler) {
            when (this) {
                is OnChangeBuilder -> handler.handleEvent(event = this)
                is OnSaveBuilder -> handler.handleEvent(event = this)
            }
        }

        data class OnSaveBuilder(val data: List<WindowAlarm>) : WindowBuilderEvent {
            constructor(data: WindowAlarm) : this(listOf(data))
        }

        data class OnChangeBuilder(val data: WindowAlarm) : WindowBuilderEvent
    }

    sealed interface WindowListingEvent : WindowEvent {
        fun resolve(handler: ListingEventHandler) {
            when (this) {
                is OnOpenAlarm -> handler.handleEvent(event = this)
                is OnCancelAlarm -> handler.handleEvent(event = this)
                is OnScheduleAlarm -> handler.handleEvent(event = this)
                is OnDeleteAlarm -> handler.handleEvent(event = this)
            }
        }

        data class OnOpenAlarm(val data: WindowAlarm) : WindowListingEvent

        data class OnCancelAlarm(val data: List<WindowAlarm>) : WindowListingEvent {
            constructor(data: WindowAlarm) : this(listOf(data))
        }

        data class OnDeleteAlarm(val data: List<WindowAlarm>) : WindowListingEvent {
            constructor(data: WindowAlarm) : this(listOf(data))
        }

        data class OnScheduleAlarm(val data: List<WindowAlarm>) : WindowListingEvent {
            constructor(data: WindowAlarm) : this(listOf(data))
        }
    }

    data class OnChangePage(val data: ListDetailPage) : WindowEvent
}
