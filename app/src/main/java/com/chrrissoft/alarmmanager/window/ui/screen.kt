package com.chrrissoft.alarmmanager.window.ui

import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.chrrissoft.alarmmanager.entities.ListDetailPage.Builder
import com.chrrissoft.alarmmanager.entities.ListDetailPage.Companion.pages
import com.chrrissoft.alarmmanager.entities.ListDetailPage.Listing
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.tags.ui.AlarmTagsEvent
import com.chrrissoft.alarmmanager.tags.ui.AlarmTagsState
import com.chrrissoft.alarmmanager.ui.AlarmManagerSnackbar
import com.chrrissoft.alarmmanager.ui.components.Screen
import com.chrrissoft.alarmmanager.ui.entities.PagesBottomBar
import com.chrrissoft.alarmmanager.utils.ComposeUtils.setBarsColors
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm
import com.chrrissoft.alarmmanager.window.ui.WindowEvent.OnChangePage
import com.chrrissoft.alarmmanager.window.ui.WindowEvent.WindowBuilderEvent.OnSaveBuilder
import com.chrrissoft.alarmmanager.window.ui.WindowEvent.WindowListingEvent.OnOpenAlarm
import com.chrrissoft.alarmmanager.window.ui.ui.ScreenContent

@Composable
fun WindowScreen(
    state: WindowState,
    onEvent: (WindowEvent) -> Unit,
    tagsState: AlarmTagsState,
    onTagsEvent: (AlarmTagsEvent) -> Unit,
    onOpenDrawer: () -> Unit,
) {
    setBarsColors()

    Screen(
        actions = {
            if (state.page == Listing) return@Screen
            IconButton(onClick = { onEvent(OnOpenAlarm(WindowAlarm())) }) {
                Icon(Rounded.Add, (null))
            }
        },
        bottomBar = {
            PagesBottomBar(
                pages = pages,
                page = state.page,
                onChangePage = { onEvent(OnChangePage(it)) },
            )
        },
        title = "One time alarms",
        floatingActionButton = {
            val builder = (state.builder as? Success)?.data ?: return@Screen
            val data = when (state.page) {
                Builder -> Rounded.Save to OnSaveBuilder(builder)
                Listing -> Rounded.Add to OnOpenAlarm(WindowAlarm())
            }
            FloatingActionButton(onClick = { onEvent(data.second) }) { Icon(data.first, (null)) }
        },
        onNavigation = { onOpenDrawer() },
        snackbarHost = { AlarmManagerSnackbar(state.snackbar) },
        content = {
            ScreenContent(
                state = state,
                windowEvent = onEvent,
                tagsState = tagsState,
                onTagsEvent = onTagsEvent,
            )
        },
    )
}
