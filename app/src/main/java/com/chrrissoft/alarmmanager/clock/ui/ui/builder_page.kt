package com.chrrissoft.alarmmanager.clock.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent.ClockBuilderEvent
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent.ClockBuilderEvent.OnChangeBuilder
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.ui.components.Page

@Composable
fun ClockBuilderPage(
    state: ResState<ClockAlarm>,
    oneTimeEvent: (ClockBuilderEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Page(state = state, modifier = modifier) { builder ->
        ClockBuilder(
            state = builder,
            onChangeState = { oneTimeEvent(OnChangeBuilder(it)) },
        )
    }
}
