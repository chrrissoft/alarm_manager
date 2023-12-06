package com.chrrissoft.alarmmanager.ui

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AlarmManagerSnackbar(
    state: SnackbarData,
    modifier: Modifier = Modifier
) {
    SnackbarHost(hostState = state.state, modifier = modifier) {
        Snackbar(
            snackbarData = it,
            containerColor = state.type.containerColor,
            contentColor = state.type.contentColor,
            actionColor = state.type.actionColor,
            actionContentColor = state.type.actionContentColor,
            dismissActionContentColor = state.type.dismissActionContentColor,
        )
    }
}
