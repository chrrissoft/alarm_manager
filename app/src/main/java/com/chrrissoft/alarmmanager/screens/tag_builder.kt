package com.chrrissoft.alarmmanager.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.AlarmsEvent.TagAlarmsEvent
import com.chrrissoft.alarmmanager.AlarmsEvent.TagAlarmsEvent.OnChangeTags
import com.chrrissoft.alarmmanager.screens.components.TagBuilder
import com.chrrissoft.alarmmanager.ui.components.TopBarTitle
import com.chrrissoft.alarmmanager.ui.theme.centerAlignedTopAppBarColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagsBuilderAlarmsScreen(
    state: Set<String>,
    onEvent: (TagAlarmsEvent) -> Unit,
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
                onClick = { }
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        },
        containerColor = colorScheme.onPrimary,
        content = { padding ->
            Box(
                Modifier
                    .padding(padding)
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp)
            ) {
                TagBuilder(
                    data = state,
                    onChangeData = { onEvent(OnChangeTags(it)) }
                )
            }
        },
    )
}
