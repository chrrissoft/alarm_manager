package com.chrrissoft.alarmmanager.window.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.screens.components.AlarmMessage
import com.chrrissoft.alarmmanager.screens.components.AlarmOperation
import com.chrrissoft.alarmmanager.screens.components.AlarmType
import com.chrrissoft.alarmmanager.screens.components.DurationInput
import com.chrrissoft.alarmmanager.screens.components.TimeChooser
import com.chrrissoft.alarmmanager.ui.components.Card
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm

@Composable
fun WindowBuilder(
    state: WindowAlarm,
    onChangeState: (WindowAlarm) -> Unit,
    modifier: Modifier = Modifier,
    tags: ResState<Set<String>>,
    onDeleteTag: ((String) -> Unit)? = null,
    onSaveTag: ((String) -> Unit)? = null,
) {
    Card(modifier) {
        AlarmType(state = state.type, onChangeState = { onChangeState(state.copy(type = it)) })
        AlarmOperation(
            state = state.operation,
            onChangeState = { onChangeState(state.copy(operation = it)) }
        )
        TimeChooser(
            time = state.time,
            onChangeTime = { onChangeState(state.copy(time = it)) },
            date = state.date,
            onChangeDate = { onChangeState(state.copy(date = it)) }
        )
        DurationInput(
            time = state.window,
            label = "Window time",
            onChangeTime = { onChangeState(state.copy(window = it)) }
        )
        AlarmMessage(
            state = state.message,
            onChangeState = { onChangeState(state.copy(message = it)) },
        )
    }
}
