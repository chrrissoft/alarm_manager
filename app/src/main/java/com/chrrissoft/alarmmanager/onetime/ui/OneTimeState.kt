package com.chrrissoft.alarmmanager.onetime.ui

import com.chrrissoft.alarmmanager.entities.ListDetailPage
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
import com.chrrissoft.alarmmanager.ui.SnackbarData

data class OneTimeState(
    val tags: Set<String> = emptySet(),
    val page: ListDetailPage = ListDetailPage.Builder,
    val snackbar: SnackbarData = SnackbarData(),
    val builder: ResState<OneTimeAlarm> = Success(OneTimeAlarm()),
    val listing: ListingOneTimeState = ListingOneTimeState(),
    val exactsAlarmEnabled: Boolean = true,
) {

    data class ListingOneTimeState(
        val selected: List<OneTimeAlarm> = emptyList(),
        val builders: ResState<List<OneTimeAlarm>> = ResState.Loading,
    )
}
