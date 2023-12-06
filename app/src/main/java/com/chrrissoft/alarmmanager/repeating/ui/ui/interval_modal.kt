package com.chrrissoft.alarmmanager.repeating.ui.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.ui.components.MyModalBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntervalModels(
    state: Pair<String, Long>,
    onChangeState: (Pair<String, Long>) -> Unit,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
) {
    MyModalBottomSheet(
        title = "Inexact Intervals",
        onDismissRequest = onDismissRequest,
        content = { IntervalList(state = state, onChangeState = onChangeState) },
        modifier = modifier
    )
}
