package com.chrrissoft.alarmmanager

import android.app.AlarmManager
import androidx.lifecycle.ViewModel
import com.chrrissoft.alarmmanager.AlarmsState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AlarmsViewModel @Inject constructor(
    private val manager: AlarmManager
) : ViewModel() {
    private val _state = MutableStateFlow(AlarmsState())
    val stateFlow = _state.asStateFlow()
    private val state get() = stateFlow.value
    private val _tags get() = state.tags
    private val _windows get() = state.windows
    private val _oneTime get() = state.oneTime
    private val _oneTimeIdle get() = state.oneTimeIdle
    private val _repeating get() = state.repeating

    private val handler = EventHandler()

    init {
    }

    fun handleEvent(event: AlarmsEvent) {
        event.resolve(handler)
    }

    inner class EventHandler {
        val tagsHandler = TagAlarmsEventHandler()
        val oneTimeHandler = OneTimeAlarmsEventHandler()
        val oneTimeIdleHandler = OneTimeIdleAlarmsEventHandler()
        val windowsHandler = WindowsAlarmsEventHandler()
        val repeatingHandler = RepeatingAlarmsEventHandler()

        inner class TagAlarmsEventHandler {
            fun onEvents(event: AlarmsEvent.TagAlarmsEvent.OnChangeTags) {
                updateState(tags = event.data)
            }

        }

        inner class OneTimeAlarmsEventHandler {
            fun onEvent(event: AlarmsEvent.OneTimeAlarmsEvent.OnChangeState) {
                updateState(oneTime = event.data)
            }

            fun onEvent(event: AlarmsEvent.OneTimeAlarmsEvent.OnLaunch) {
                TODO("Not yet implemented")
            }

            fun onEvent(event: AlarmsEvent.OneTimeAlarmsEvent.OnCancel) {
                TODO("Not yet implemented")
            }

            fun onEvent(event: AlarmsEvent.OneTimeAlarmsEvent.OnCreate) {
                val listing = _oneTime.listing.plus(event.data)
                val state = _oneTime.copy(listing = listing)
                updateState(oneTime = state)
            }
        }

        inner class OneTimeIdleAlarmsEventHandler {
            fun onEvent(event: AlarmsEvent.OneTimeIdleAlarmsEvent.OnChangeState) {
                updateState(oneTimeIdle = event.data)
            }

            fun onEvent(event: AlarmsEvent.OneTimeIdleAlarmsEvent.OnLaunch) {
                TODO("Not yet implemented")
            }

            fun onEvent(event: AlarmsEvent.OneTimeIdleAlarmsEvent.OnCancel) {
                TODO("Not yet implemented")
            }

            fun onEvent(event: AlarmsEvent.OneTimeIdleAlarmsEvent.OnCreate) {
                val listing = _oneTimeIdle.listing.plus(event.data)
                val state = _oneTimeIdle.copy(listing = listing)
                updateState(oneTimeIdle = state)
            }
        }

        inner class WindowsAlarmsEventHandler {
            fun onEvent(event: AlarmsEvent.WindowsAlarmsEvent.OnChangeState) {
                updateState(windows = event.data)
            }

            fun onEvent(event: AlarmsEvent.WindowsAlarmsEvent.OnLaunch) {
                TODO("Not yet implemented")
            }

            fun onEvent(event: AlarmsEvent.WindowsAlarmsEvent.OnCancel) {
                TODO("Not yet implemented")
            }

            fun onEvent(event: AlarmsEvent.WindowsAlarmsEvent.OnCreate) {
                val listing = _windows.listing.plus(event.data)
                val state = _windows.copy(listing = listing)
                updateState(windows = state)
            }
        }

        inner class RepeatingAlarmsEventHandler {
            fun onEvent(event: AlarmsEvent.RepeatingAlarmsEvent.OnChangeState) {
                updateState(repeating = event.data)
            }

            fun onEvent(event: AlarmsEvent.RepeatingAlarmsEvent.OnLaunch) {
                TODO("Not yet implemented")
            }

            fun onEvent(event: AlarmsEvent.RepeatingAlarmsEvent.OnCancel) {
                TODO("Not yet implemented")
            }

            fun onEvent(event: AlarmsEvent.RepeatingAlarmsEvent.OnCreate) {
                val listing = _repeating.listing.plus(event.data)
                val state = _repeating.copy(listing = listing)
                updateState(repeating = state)
            }
        }
    }


    private fun updateState(
        tags: Set<String> = _tags,
        oneTime: OneTimeState = _oneTime,
        oneTimeIdle: OneTimeIdleState = _oneTimeIdle,
        windows: WindowsAlarmsState = _windows,
        repeating: RepeatingAlarmsState = _repeating,
    ) {
        _state.update {
            it.copy(
                tags = tags,
                oneTime = oneTime,
                oneTimeIdle = oneTimeIdle,
                windows = windows,
                repeating = repeating,
            )
        }
    }
}
