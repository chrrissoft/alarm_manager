package com.chrrissoft.alarmmanager.tags.ui

import com.chrrissoft.alarmmanager.tags.ui.AlarmTagsViewModel.EvenHandler

sealed interface AlarmTagsEvent {
    fun resolve(handler: EvenHandler) {
        when (this) {
            is OnDelete -> handler.handleEvent(event = this)
            is OnSave -> handler.handleEvent(event = this)
        }
    }

    data class OnSave(val data: String) : AlarmTagsEvent

    data class OnDelete(val data: String) : AlarmTagsEvent
}
