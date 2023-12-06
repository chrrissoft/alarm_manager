package com.chrrissoft.alarmmanager.repeating.ui

import com.chrrissoft.alarmmanager.entities.ListDetailPage
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingViewModel.EvenHandler
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingViewModel.EvenHandler.ListingEventHandler

sealed interface RepeatingEvent {
    fun resolve(handler: EvenHandler) {
        when (this) {
            is RepeatingBuilderEvent -> resolve(handler.builderHandler)
            is RepeatingListingEvent -> resolve(handler.listingHandler)
            is OnChangePage -> handler.handleEvent(event = this)
        }
    }

    sealed interface RepeatingBuilderEvent : RepeatingEvent {
        fun resolve(handler: EvenHandler.BuilderEventHandler) {
            when (this) {
                is OnChangeBuilder -> handler.handleEvent(event = this)
                is OnSaveBuilder -> handler.handleEvent(event = this)
            }
        }

        data class OnSaveBuilder(val data: List<RepeatingAlarm>) : RepeatingBuilderEvent {
            constructor(data: RepeatingAlarm) : this(listOf(data))
        }

        data class OnChangeBuilder(val data: RepeatingAlarm) : RepeatingBuilderEvent
    }

    sealed interface RepeatingListingEvent : RepeatingEvent {
        fun resolve(handler: ListingEventHandler) {
            when (this) {
                is OnOpenAlarm -> handler.handleEvent(event = this)
                is OnCancelAlarm -> handler.handleEvent(event = this)
                is OnScheduleAlarm -> handler.handleEvent(event = this)
                is OnDeleteAlarm -> handler.handleEvent(event = this)
            }
        }

        data class OnOpenAlarm(val data: RepeatingAlarm) : RepeatingListingEvent

        data class OnCancelAlarm(val data: List<RepeatingAlarm>) : RepeatingListingEvent {
            constructor(data: RepeatingAlarm) : this(listOf(data))
        }

        data class OnDeleteAlarm(val data: List<RepeatingAlarm>) : RepeatingListingEvent {
            constructor(data: RepeatingAlarm) : this(listOf(data))
        }

        data class OnScheduleAlarm(val data: List<RepeatingAlarm>) : RepeatingListingEvent {
            constructor(data: RepeatingAlarm) : this(listOf(data))
        }
    }

    data class OnChangePage(val data: ListDetailPage) : RepeatingEvent
}
