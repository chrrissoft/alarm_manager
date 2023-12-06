package com.chrrissoft.alarmmanager.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
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
import com.chrrissoft.alarmmanager.clock.ui.ClockScreen
import com.chrrissoft.alarmmanager.clock.ui.ClockViewModel
import com.chrrissoft.alarmmanager.navigation.Screens.CLOCK
import com.chrrissoft.alarmmanager.navigation.Screens.Companion.screens
import com.chrrissoft.alarmmanager.navigation.Screens.ONE_TIME
import com.chrrissoft.alarmmanager.navigation.Screens.REPEATING
import com.chrrissoft.alarmmanager.navigation.Screens.TAG_BUILDER
import com.chrrissoft.alarmmanager.navigation.Screens.WINDOWS
import com.chrrissoft.alarmmanager.onetime.ui.OneTimeScreen
import com.chrrissoft.alarmmanager.onetime.ui.OneTimeViewModel
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingScreen
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingViewModel
import com.chrrissoft.alarmmanager.tags.ui.AlarmTagsScreen
import com.chrrissoft.alarmmanager.tags.ui.AlarmTagsViewModel
import com.chrrissoft.alarmmanager.ui.theme.navigationDrawerItemColors
import com.chrrissoft.alarmmanager.utils.NavigationUtils.close
import com.chrrissoft.alarmmanager.utils.NavigationUtils.open
import com.chrrissoft.alarmmanager.window.ui.WindowScreen
import com.chrrissoft.alarmmanager.window.ui.WindowViewModel

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
                            selected = backStack?.destination?.route == it.route,
                            onClick = {
                                drawerState.close(scope)
                                if (!controller.popBackStack(it.route, inclusive = false)) {
                                    controller.navigate(it.route) { launchSingleTop = true }
                                }
                            },
                            shape = shapes.medium,
                            icon = { Icon(it.icon, (null)) },
                            colors = navigationDrawerItemColors,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        },
    ) {
        val tagsViewModel = hiltViewModel<AlarmTagsViewModel>()
        val tagsState by tagsViewModel.stateFlow.collectAsState()

        NavHost(navController = controller, startDestination = ONE_TIME.route) {
            composable(ONE_TIME.route) {
                val viewModel = hiltViewModel<OneTimeViewModel>()
                val state by viewModel.stateFlow.collectAsState()
                OneTimeScreen(
                    state = state,
                    onEvent = { viewModel.handleEvent(it) },
                    tagsState = tagsState,
                    onTagsEvent = { tagsViewModel.handleEvent(it) },
                    onOpenDrawer = { drawerState.open(scope) }
                )
            }

            composable(WINDOWS.route) {
                val viewModel = hiltViewModel<WindowViewModel>()
                val state by viewModel.stateFlow.collectAsState()
                WindowScreen(
                    state = state,
                    onEvent = { viewModel.handleEvent(it) },
                    tagsState = tagsState,
                    onTagsEvent = { tagsViewModel.handleEvent(it) },
                    onOpenDrawer = { drawerState.open(scope) }
                )
            }

            composable(REPEATING.route) {
                val viewModel = hiltViewModel<RepeatingViewModel>()
                val state by viewModel.stateFlow.collectAsState()
                RepeatingScreen(
                    state = state,
                    onEvent = { viewModel.handleEvent(it) },
                    onOpenDrawer = { drawerState.open(scope) }
                )
            }

            composable(CLOCK.route) {
                val viewModel = hiltViewModel<ClockViewModel>()
                val state by viewModel.stateFlow.collectAsState()
                ClockScreen(
                    state = state,
                    onEvent = { viewModel.handleEvent(it) },
                    onOpenDrawer = { drawerState.open(scope) }
                )
            }

            composable(TAG_BUILDER.route) {
                AlarmTagsScreen(
                    state = tagsState,
                    onEvent = { tagsViewModel.handleEvent(it) },
                    onOpenDrawer = { drawerState.open(scope) }
                )
            }
        }
    }
}
