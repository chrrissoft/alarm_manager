package com.chrrissoft.alarmmanager.window.ui

import com.chrrissoft.alarmmanager.entities.ListDetailPage
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.ui.SnackbarData
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm

data class WindowState(
    val tags: Set<String> = emptySet(),
    val page: ListDetailPage = ListDetailPage.Builder,
    val snackbar: SnackbarData = SnackbarData(),
    val builder: ResState<WindowAlarm> = Success(WindowAlarm()),
    val listing: ListingWindowState = ListingWindowState(),
) {
    data class ListingWindowState(
        val selected: List<WindowAlarm> = emptyList(),
        val builders: ResState<List<WindowAlarm>> = ResState.Loading,
    )
}
