package com.chrrissoft.alarmmanager.screens.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.base.AlarmBuilder
import com.chrrissoft.alarmmanager.base.AlarmBuilder.AlarmBuilderOpenOperation

@Composable
fun <O : AlarmBuilderOpenOperation> ListingPage(
    tags: Set<String>,
    state: List<O>,
    onLaunch: (O) -> Unit,
    onCancel: (O) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier) {
        items(state) { builder ->
            AlarmItem(
                data = builder,
                tags = tags,
                onCancel = { onCancel(it) },
                onLaunch = { onLaunch(it) },
                modifier = modifier,
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun <O : AlarmBuilder.AlarmBuilderPendingIntentOperation> ListingPage(
    state: List<O>,
    onLaunch: (O) -> Unit,
    onCancel: (O) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier) {
        items(state) { builder ->
            AlarmItem(
                data = builder,
                onCancel = { onCancel(it) },
                onLaunch = { onLaunch(it) },
                modifier = modifier,
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
