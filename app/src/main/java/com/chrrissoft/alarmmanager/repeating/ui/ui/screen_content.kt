package com.chrrissoft.alarmmanager.repeating.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.entities.ListDetailPage
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingEvent
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingState

@Composable
fun ScreenContent(
    state: RepeatingState,
    oneTimeEvent: (RepeatingEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state.page) {
        ListDetailPage.Builder -> RepeatingBuilderPage(state.builder, oneTimeEvent, modifier)
        ListDetailPage.Listing -> RepeatingListingPage(state.listing, oneTimeEvent, modifier)
    }
}
