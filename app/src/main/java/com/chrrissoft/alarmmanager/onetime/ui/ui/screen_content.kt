package com.chrrissoft.alarmmanager.onetime.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.entities.ListDetailPage
import com.chrrissoft.alarmmanager.onetime.ui.OneTimeEvent
import com.chrrissoft.alarmmanager.onetime.ui.OneTimeState
import com.chrrissoft.alarmmanager.tags.ui.AlarmTagsEvent
import com.chrrissoft.alarmmanager.tags.ui.AlarmTagsEvent.OnDelete
import com.chrrissoft.alarmmanager.tags.ui.AlarmTagsEvent.OnSave
import com.chrrissoft.alarmmanager.tags.ui.AlarmTagsState

@Composable
fun ScreenContent(
    state: OneTimeState,
    oneTimeEvent: (OneTimeEvent) -> Unit,
    tagsState: AlarmTagsState,
    onTagsEvent: (AlarmTagsEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state.page) {
        ListDetailPage.Builder -> {
            OneTimeBuilderPage(
                state = state.builder,
                oneTimeEvent = oneTimeEvent,
                tags = tagsState.data,
                onDeleteTag = { onTagsEvent(OnDelete(it)) },
                onSaveTag = { onTagsEvent(OnSave(it)) },
                modifier = modifier
            )
        }
        ListDetailPage.Listing -> OneTimeListingPage(state.listing, oneTimeEvent, modifier)
    }
}
