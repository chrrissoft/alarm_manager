package com.chrrissoft.alarmmanager.screens.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.base.AlarmBuilder.AlarmBuilderOpenOperation
import com.chrrissoft.alarmmanager.base.AlarmBuilder.AlarmBuilderPendingIntentOperation
import com.chrrissoft.alarmmanager.base.AlarmOperation
import com.chrrissoft.alarmmanager.base.AlarmOperation.PendingIntentOperation
import com.chrrissoft.alarmmanager.ui.theme.cardColors

@Composable
fun <T : AlarmBuilderOpenOperation> AlarmItem(
    data: T,
    onCancel: (T) -> Unit,
    onLaunch: (T) -> Unit,
    tags: Set<String>,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = cardColors,
        modifier = modifier.animateContentSize(animationSpec = animation),
    ) {
        var showInfo by remember {
            mutableStateOf(false)
        }
        Column(modifier = Modifier.padding(10.dp)) {
            if (showInfo) {
                if (data.operation !is PendingIntentOperation) {
                    TagChooser(
                        data = tags,
                        selected = data.tag,
                        onChangeSelected = { },
                    )
                }

                val operationList = when (data) {
                    is AlarmBuilderPendingIntentOperation -> PendingIntentOperation.list
                    else -> AlarmOperation.list
                }

                AlarmOperationUi(
                    list = operationList,
                    data = data.operation,
                    onChangeData = {},
                )
                AlarmTypeUi(data = data.type, onChangeData = { })
            }

            AlarmTime(data = data.time, onChangeData = { })
            ItemActions(
                running = data.running,
                showingInfo = showInfo,
                onCancel = { onCancel(data) },
                onLaunch = { onLaunch(data) },
                onShowInfo = { showInfo = it },
            )
        }
    }
}

@Composable
fun <T : AlarmBuilderPendingIntentOperation> AlarmItem(
    data: T,
    onCancel: (T) -> Unit,
    onLaunch: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = cardColors,
        modifier = modifier.animateContentSize(animationSpec = animation),
    ) {
        var showInfo by remember {
            mutableStateOf(false)
        }
        Column(modifier = Modifier.padding(10.dp)) {
            if (showInfo) {
                AlarmOperationUi(
                    list = PendingIntentOperation.list,
                    data = data.operation,
                    onChangeData = {},
                )
                AlarmTypeUi(data = data.type, onChangeData = { })
            }

            AlarmTime(data = data.time, onChangeData = { })
            ItemActions(
                running = data.running,
                showingInfo = showInfo,
                onCancel = { onCancel(data) },
                onLaunch = { onLaunch(data) },
                onShowInfo = { showInfo = it },
            )
        }
    }
}


@Composable
private fun ItemActions(
    onCancel: () -> Unit,
    onLaunch: () -> Unit,
    onShowInfo: (Boolean) -> Unit,
    showingInfo: Boolean,
    running: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Button(
            enabled = !running,
            content = { Text(text = "Launch") },
            shape = MaterialTheme.shapes.medium,
            onClick = { onLaunch() },
            modifier = Modifier.weight(1f),
        )
        Spacer(modifier = Modifier.weight(.05f))
        Button(
            content = { Text(text = "Cancel") },
            shape = MaterialTheme.shapes.medium,
            onClick = { onCancel() },
            modifier = Modifier.weight(1f),
        )
        Spacer(modifier = Modifier.weight(.05f))
        Button(
            content = { Text(text = "Show info") },
            shape = MaterialTheme.shapes.medium,
            onClick = { onShowInfo(!showingInfo) },
            modifier = Modifier.weight(1f),
        )
    }
}
