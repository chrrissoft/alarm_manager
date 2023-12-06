package com.chrrissoft.alarmmanager.utils

import androidx.compose.material3.DrawerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object NavigationUtils {
    fun DrawerState.open(scope: CoroutineScope) {
        scope.launch { open() }
    }

    fun DrawerState.close(scope: CoroutineScope) {
        scope.launch { close() }
    }
}
