package com.chrrissoft.alarmmanager.window.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.entities.ListDetailPage
import com.chrrissoft.alarmmanager.tags.ui.AlarmTagsEvent
import com.chrrissoft.alarmmanager.tags.ui.AlarmTagsEvent.OnDelete
import com.chrrissoft.alarmmanager.tags.ui.AlarmTagsEvent.OnSave
import com.chrrissoft.alarmmanager.tags.ui.AlarmTagsState
import com.chrrissoft.alarmmanager.window.ui.WindowEvent
import com.chrrissoft.alarmmanager.window.ui.WindowState

@Composable
fun ScreenContent(
    state: WindowState,
    windowEvent: (WindowEvent) -> Unit,
    tagsState: AlarmTagsState,
    onTagsEvent: (AlarmTagsEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state.page) {
        ListDetailPage.Builder -> {
            WindowBuilderPage(
                state = state.builder,
                windowEvent = windowEvent,
                tags = tagsState.data,
                onDeleteTag = { onTagsEvent(OnDelete(it)) },
                onSaveTag = { onTagsEvent(OnSave(it)) },
                modifier = modifier
            )
        }
        ListDetailPage.Listing -> WindowListingPage(state.listing, windowEvent, modifier)
    }
}
