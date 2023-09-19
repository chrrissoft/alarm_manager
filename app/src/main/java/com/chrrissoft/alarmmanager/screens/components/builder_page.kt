package com.chrrissoft.alarmmanager.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.base.AlarmBuilder

@Composable
fun <O : AlarmBuilder.AlarmBuilderOpenOperation> BuilderPage(
    tags: Set<String>,
    state: O,
    onChangeState: (O) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        AlarmBuilderUi(
            tags = tags,
            data = state,
            onChangeData = { onChangeState(it) },
        )
    }
}

@Composable
fun <O : AlarmBuilder.AlarmBuilderPendingIntentOperation> BuilderPage(
    state: O,
    onChangeState: (O) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        AlarmBuilderUi(data = state, onChangeData = onChangeState)
    }
}
