package com.chrrissoft.alarmmanager.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.AlarmsEvent.WindowsAlarmsEvent
import com.chrrissoft.alarmmanager.AlarmsEvent.WindowsAlarmsEvent.*
import com.chrrissoft.alarmmanager.AlarmsState
import com.chrrissoft.alarmmanager.AlarmsState.Page.BUILDER
import com.chrrissoft.alarmmanager.AlarmsState.Page.LISTING
import com.chrrissoft.alarmmanager.AlarmsState.WindowsAlarmsState
import com.chrrissoft.alarmmanager.screens.components.BuilderPage
import com.chrrissoft.alarmmanager.screens.components.ListingPage
import com.chrrissoft.alarmmanager.ui.components.TopBarTitle
import com.chrrissoft.alarmmanager.ui.theme.centerAlignedTopAppBarColors
import com.chrrissoft.alarmmanager.ui.theme.navigationBarItemColors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WindowsAlarmsScreen(
    state: WindowsAlarmsState,
    onEvent: (WindowsAlarmsEvent) -> Unit,
    tags: Set<String>,
    onOpenDrawer: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = centerAlignedTopAppBarColors,
                navigationIcon = {
                    IconButton(onClick = { onOpenDrawer() }) {
                        Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
                    }
                },
                title = { TopBarTitle(title = "Windows Alarms") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEvent(OnCreate(state.builder)) }
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = colorScheme.primaryContainer,
                contentColor = colorScheme.onPrimaryContainer,
            ) {
                AlarmsState.Page.pages.forEach {
                    NavigationBarItem(
                        selected = state.page==it,
                        colors = navigationBarItemColors,
                        onClick = {
                            onEvent(OnChangeState(state.copy(page = it)))
                        },
                        label = { Text(text = it.label) },
                        icon = { Icon(imageVector = it.icon, contentDescription = null) }
                    )
                }
            }
        },
        containerColor = colorScheme.onPrimary,
        content = { padding ->
            ScreenContent(
                state = state,
                onEvent = onEvent,
                tags = tags,
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp)
            )
        },
    )
}


@Composable
private fun ScreenContent(
    tags: Set<String>,
    state: WindowsAlarmsState,
    onEvent: (WindowsAlarmsEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state.page) {
        BUILDER -> {
            BuilderPage(
                tags = tags,
                state = state.builder,
                onChangeState = { onEvent(OnChangeState(state.copy(builder = it))) },
                modifier = modifier,
            )
        }
        LISTING -> {
            ListingPage(
                tags = tags,
                state = state.listing,
                onLaunch = { onEvent(OnLaunch(it)) },
                onCancel = { onEvent(OnCancel(it)) },
                modifier = modifier,
            )
        }
    }
}
