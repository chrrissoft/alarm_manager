package com.chrrissoft.alarmmanager

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.material3.SnackbarDuration.Indefinite
import androidx.compose.material3.SnackbarDuration.Short
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

object Util {
    @SuppressLint("ComposableNaming")
    @Composable
    fun setBarsColors(
        status: Color = MaterialTheme.colorScheme.primaryContainer,
        bottom: Color = MaterialTheme.colorScheme.primaryContainer,
    ) {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()

        DisposableEffect(systemUiController, useDarkIcons) {
            systemUiController.setStatusBarColor(status, useDarkIcons)
            systemUiController.setNavigationBarColor(bottom)
            onDispose {}
        }
    }

    fun DrawerState.open(scope: CoroutineScope) {
        scope.launch { open() }
    }

    fun DrawerState.close(scope: CoroutineScope) {
        scope.launch { close() }
    }

    val String.ui get() = replace("_", " ").lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

    fun SnackbarHostState.showSnackbarOwn(
        message: String,
        actionLabel: String? = null,
        withDismissAction: Boolean = actionLabel != null,
        duration: SnackbarDuration = if (actionLabel==null) Short else Indefinite,
        scope: CoroutineScope,
        onPerformed: (() -> Unit)? = null,
        dismissCurrent: Boolean = true,
    ) {
        if (dismissCurrent) this.currentSnackbarData?.dismiss()
        scope.launch {
            showSnackbar(message, actionLabel, withDismissAction, duration).let {
                if (it==SnackbarResult.ActionPerformed) onPerformed?.let { it1 -> it1() }
            }
        }
    }
}
