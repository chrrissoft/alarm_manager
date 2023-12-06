package com.chrrissoft.alarmmanager.onetime.ui.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.onetime.ui.OneTimeEvent.OneTimeListingEvent
import com.chrrissoft.alarmmanager.onetime.ui.OneTimeEvent.OneTimeListingEvent.OnCancelAlarm
import com.chrrissoft.alarmmanager.onetime.ui.OneTimeEvent.OneTimeListingEvent.OnDeleteAlarm
import com.chrrissoft.alarmmanager.onetime.ui.OneTimeEvent.OneTimeListingEvent.OnOpenAlarm
import com.chrrissoft.alarmmanager.onetime.ui.OneTimeEvent.OneTimeListingEvent.OnScheduleAlarm
import com.chrrissoft.alarmmanager.onetime.ui.OneTimeState
import com.chrrissoft.alarmmanager.ui.components.Page

@Composable
fun OneTimeListingPage(
    state: OneTimeState.ListingOneTimeState,
    onEvent: (OneTimeListingEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Page(state = state.builders, modifier = modifier) {
        LazyColumn {
            items(it) {
                OneTimeItem(
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
