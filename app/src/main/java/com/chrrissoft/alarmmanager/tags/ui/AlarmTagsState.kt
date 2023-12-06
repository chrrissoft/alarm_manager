package com.chrrissoft.alarmmanager.tags.ui

import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.ui.SnackbarData

data class AlarmTagsState(
    val snackbar: SnackbarData = SnackbarData(),
    val data: ResState<Set<String>> = ResState.Loading,
)
