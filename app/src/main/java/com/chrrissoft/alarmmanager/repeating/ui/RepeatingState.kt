package com.chrrissoft.alarmmanager.repeating.ui

import com.chrrissoft.alarmmanager.entities.ListDetailPage
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import com.chrrissoft.alarmmanager.ui.SnackbarData

data class RepeatingState(
    val page: ListDetailPage = ListDetailPage.Builder,
    val snackbar: SnackbarData = SnackbarData(),
    val builder: ResState<RepeatingAlarm> = Success(RepeatingAlarm()),
    val listing: ListingRepeatingState = ListingRepeatingState(),
    val exactsAlarmEnabled: Boolean = true,
) {
    data class ListingRepeatingState(
        val selected: List<RepeatingAlarm> = emptyList(),
        val builders: ResState<List<RepeatingAlarm>> = ResState.Loading,
    )
}
