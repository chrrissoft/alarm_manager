package com.chrrissoft.alarmmanager.repeating.ui.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingEvent.RepeatingListingEvent
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingEvent.RepeatingListingEvent.OnCancelAlarm
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingEvent.RepeatingListingEvent.OnDeleteAlarm
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingEvent.RepeatingListingEvent.OnOpenAlarm
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingEvent.RepeatingListingEvent.OnScheduleAlarm
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingState
import com.chrrissoft.alarmmanager.ui.components.Page

@Composable
fun RepeatingListingPage(
    state: RepeatingState.ListingRepeatingState,
    onEvent: (RepeatingListingEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Page(state = state.builders, modifier = modifier) {
        LazyColumn {
            items(it) {
                RepeatingItem(
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
