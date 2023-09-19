package com.chrrissoft.alarmmanager.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chrrissoft.alarmmanager.Util.close
import com.chrrissoft.alarmmanager.Util.open
import com.chrrissoft.alarmmanager.navigation.Screens.*
import com.chrrissoft.alarmmanager.navigation.Screens.Companion.screens
import com.chrrissoft.alarmmanager.AlarmsViewModel
import com.chrrissoft.alarmmanager.screens.*
import com.chrrissoft.alarmmanager.ui.theme.navigationDrawerItemColors

@Composable
fun Graph() {
    val controller = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = Closed)
    val scope = rememberCoroutineScope()
    val backStack by controller.currentBackStackEntryAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = colorScheme.primaryContainer
            ) {
                DrawerHeader()
                LazyColumn {
                    items(screens) {
                        NavigationDrawerItem(
                            label = { Text(text = it.label) },
                            selected = backStack?.destination?.route==it.route,
                            onClick = {
                                drawerState.close(scope)
                                if (!controller.popBackStack(it.route, false)) {
                                    controller.navigate(it.route) {
                                        launchSingleTop = true
                                    }
                                }
                            },
                            icon = {
                                Icon(imageVector = it.icon, contentDescription = null)
                            },
                            colors = navigationDrawerItemColors,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        },
    ) {
        val viewModel = hiltViewModel<AlarmsViewModel>()
        val state by viewModel.stateFlow.collectAsState()

        NavHost(controller, ONE_TIME.route) {
            composable(ONE_TIME.route) {
                OneTimeAlarmsScreen(
                    tags = state.tags,
                    state = state.oneTime,
                    onEvent = { viewModel.handleEvent(it) },
                    onOpenDrawer = { drawerState.open(scope) }
                )
            }

            composable(ONE_TIME_IDLE.route) {
                OneTimeIdleAlarmsScreen(
                    state = state.oneTimeIdle,
                    onEvent = { viewModel.handleEvent(it) },
                    onOpenDrawer = { drawerState.open(scope) }
                )
            }

            composable(WINDOWS.route) {
                WindowsAlarmsScreen(
                    tags = state.tags,
                    state = state.windows,
                    onEvent = { viewModel.handleEvent(it) },
                    onOpenDrawer = { drawerState.open(scope) },
                )
            }

            composable(REPEATING.route) {
                RepeatingAlarmsScreen(
                    state = state.repeating,
                    onEvent = { viewModel.handleEvent(it) },
                    onOpenDrawer = { drawerState.open(scope) }
                )
            }

            composable(TAG_BUILDER.route) {
                TagsBuilderAlarmsScreen(
                    state = state.tags,
                    onEvent = { viewModel.handleEvent(it) },
                    onOpenDrawer = { drawerState.open(scope) },
                )
            }
        }
    }
}
