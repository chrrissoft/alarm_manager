package com.chrrissoft.alarmmanager.window.ui.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.ui.components.Page
import com.chrrissoft.alarmmanager.window.ui.WindowEvent.WindowListingEvent
import com.chrrissoft.alarmmanager.window.ui.WindowEvent.WindowListingEvent.OnCancelAlarm
import com.chrrissoft.alarmmanager.window.ui.WindowEvent.WindowListingEvent.OnDeleteAlarm
import com.chrrissoft.alarmmanager.window.ui.WindowEvent.WindowListingEvent.OnOpenAlarm
import com.chrrissoft.alarmmanager.window.ui.WindowEvent.WindowListingEvent.OnScheduleAlarm
import com.chrrissoft.alarmmanager.window.ui.WindowState

@Composable
fun WindowListingPage(
    state: WindowState.ListingWindowState,
    onEvent: (WindowListingEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Page(state = state.builders, modifier = modifier) {
        LazyColumn {
            items(it) {
                WindowItem(
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
