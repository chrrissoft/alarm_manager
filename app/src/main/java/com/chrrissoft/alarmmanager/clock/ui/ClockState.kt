package com.chrrissoft.alarmmanager.clock.ui

import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.entities.ListDetailPage
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.ui.SnackbarData

data class ClockState(
    val tags: Set<String> = emptySet(),
    val page: ListDetailPage = ListDetailPage.Builder,
    val snackbar: SnackbarData = SnackbarData(),
    val builder: ResState<ClockAlarm> = Success(ClockAlarm()),
    val listing: ListingClockState = ListingClockState(),
) {
    data class ListingClockState(
        val selected: List<ClockAlarm> = emptyList(),
        val builders: ResState<List<ClockAlarm>> = ResState.Loading,
    )
}
