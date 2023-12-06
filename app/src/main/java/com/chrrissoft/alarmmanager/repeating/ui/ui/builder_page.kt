package com.chrrissoft.alarmmanager.repeating.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingEvent.RepeatingBuilderEvent
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingEvent.RepeatingBuilderEvent.OnChangeBuilder
import com.chrrissoft.alarmmanager.ui.components.Page

@Composable
fun RepeatingBuilderPage(
    state: ResState<RepeatingAlarm>,
    oneTimeEvent: (RepeatingBuilderEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Page(state = state, modifier = modifier) { builder ->
        RepeatingBuilder(state = builder, onChangeState = { oneTimeEvent(OnChangeBuilder(it)) })
    }
}
