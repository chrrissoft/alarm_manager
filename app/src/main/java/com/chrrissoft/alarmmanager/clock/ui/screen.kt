package com.chrrissoft.alarmmanager.clock.ui

import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent.ClockBuilderEvent.OnSaveBuilder
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent.ClockListingEvent.OnOpenAlarm
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent.OnChangePage
import com.chrrissoft.alarmmanager.clock.ui.ui.ScreenContent
import com.chrrissoft.alarmmanager.entities.ListDetailPage.Builder
import com.chrrissoft.alarmmanager.entities.ListDetailPage.Companion.pages
import com.chrrissoft.alarmmanager.entities.ListDetailPage.Listing
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.ui.AlarmManagerSnackbar
import com.chrrissoft.alarmmanager.ui.components.ExactsAlarmsDisableCard
import com.chrrissoft.alarmmanager.ui.components.Screen
import com.chrrissoft.alarmmanager.ui.entities.PagesBottomBar
import com.chrrissoft.alarmmanager.utils.ComposeUtils.setBarsColors

@Composable
fun ClockScreen(
    state: ClockState,
    onEvent: (ClockEvent) -> Unit,
    onOpenDrawer: () -> Unit,
) {
    setBarsColors()

    Screen(
        actions = {
            if (state.page == Listing) return@Screen
            IconButton(onClick = { onEvent(OnOpenAlarm(ClockAlarm())) }) {
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
                Listing -> Rounded.Add to OnOpenAlarm(ClockAlarm())
            }
            FloatingActionButton(onClick = { onEvent(data.second) }) { Icon(data.first, (null)) }
        },
        onNavigation = { onOpenDrawer() },
        snackbarHost = { AlarmManagerSnackbar(state.snackbar) },
        content = {
            if (state.exactsAlarmEnabled) ExactsAlarmsDisableCard()
            ScreenContent(state = state, oneTimeEvent = onEvent)
        },
    )
}
