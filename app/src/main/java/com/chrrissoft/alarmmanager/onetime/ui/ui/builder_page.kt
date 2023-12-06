package com.chrrissoft.alarmmanager.onetime.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
import com.chrrissoft.alarmmanager.onetime.ui.OneTimeEvent.OneTimeBuilderEvent
import com.chrrissoft.alarmmanager.onetime.ui.OneTimeEvent.OneTimeBuilderEvent.OnChangeBuilder
import com.chrrissoft.alarmmanager.ui.components.Page

@Composable
fun OneTimeBuilderPage(
    state: ResState<OneTimeAlarm>,
    oneTimeEvent: (OneTimeBuilderEvent) -> Unit,
    tags: ResState<Set<String>>,
    modifier: Modifier = Modifier,
    onDeleteTag: ((String) -> Unit)? = null,
    onSaveTag: ((String) -> Unit)? = null,
) {
    Page(state = state, modifier = modifier) { builder ->
        OneTimeBuilder(
            tags = tags,
            state = builder,
            onSaveTag = onSaveTag,
            onDeleteTag = onDeleteTag,
            onChangeState = { oneTimeEvent(OnChangeBuilder(it)) },
        )
    }
}
