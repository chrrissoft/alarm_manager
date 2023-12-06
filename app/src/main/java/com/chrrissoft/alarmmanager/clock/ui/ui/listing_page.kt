package com.chrrissoft.alarmmanager.clock.ui.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent.ClockListingEvent
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent.ClockListingEvent.OnCancelAlarm
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent.ClockListingEvent.OnDeleteAlarm
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent.ClockListingEvent.OnOpenAlarm
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent.ClockListingEvent.OnScheduleAlarm
import com.chrrissoft.alarmmanager.clock.ui.ClockState
import com.chrrissoft.alarmmanager.ui.components.Page

@Composable
fun ClockListingPage(
    state: ClockState.ListingClockState,
    onEvent: (ClockListingEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Page(state = state.builders, modifier = modifier) {
        LazyColumn {
            items(it) {
                ClockItem(
                    state = it,
                    onOpen = { onEvent(OnOpenAlarm(it)) },
                    onCancel = { onEvent(OnCancelAlarm(listOf(it))) },
                    onDelete = { onEvent(OnDeleteAlarm(listOf(it))) },
                    onSchedule = { onEvent(OnScheduleAlarm(listOf(it))) },
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}
