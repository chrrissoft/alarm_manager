package com.chrrissoft.alarmmanager.clock.ui.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.clock.entities.ClockInfo
import com.chrrissoft.alarmmanager.entities.AlarmOperation.Companion.repeatingOperations
import com.chrrissoft.alarmmanager.screens.components.AlarmOperation
import com.chrrissoft.alarmmanager.screens.components.TimeChooser

@Composable
fun ClockInfo(
    state: ClockInfo,
    onChangeState: (ClockInfo) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        TimeChooser(
            time = state.time,
            onChangeTime = { onChangeState(state.copy(time = it)) },
            date = state.date,
            onChangeDate = { onChangeState(state.copy(date = it)) }
        )
        AlarmOperation(
            state = state.component,
            operations = repeatingOperations,
            onChangeState = { onChangeState(state.copy(component = it)) }
        )
    }
}