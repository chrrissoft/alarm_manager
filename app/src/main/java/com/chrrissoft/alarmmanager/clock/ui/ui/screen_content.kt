package com.chrrissoft.alarmmanager.clock.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent
import com.chrrissoft.alarmmanager.clock.ui.ClockState
import com.chrrissoft.alarmmanager.entities.ListDetailPage

@Composable
fun ScreenContent(
    state: ClockState,
    oneTimeEvent: (ClockEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state.page) {
        ListDetailPage.Builder -> ClockBuilderPage(state.builder, oneTimeEvent, modifier)
        ListDetailPage.Listing -> ClockListingPage(state.listing, oneTimeEvent, modifier)
    }
}
