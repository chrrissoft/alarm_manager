package com.chrrissoft.alarmmanager.onetime.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.entities.AlarmOperation
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.onetime.entities.OneTimeAlarm
import com.chrrissoft.alarmmanager.screens.components.AlarmMessage
import com.chrrissoft.alarmmanager.screens.components.AlarmOperation
import com.chrrissoft.alarmmanager.screens.components.AlarmType
import com.chrrissoft.alarmmanager.screens.components.IdleAndExactAndTag
import com.chrrissoft.alarmmanager.screens.components.TimeChooser
import com.chrrissoft.alarmmanager.ui.components.Card

@Composable
fun OneTimeBuilder(
    state: OneTimeAlarm,
    onChangeState: (OneTimeAlarm) -> Unit,
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
        IdleAndExactAndTag(
            idle = state.idle,
            exact = state.exact,
            tag = state.tag,
            tags = tags,
            tagEnabled = state.operation == AlarmOperation.Local,
            onIdleChange = { onChangeState(state.copy(idle = it)) },
            onExactChange = { onChangeState(state.copy(exact = it)) },
            onTagChange = { onChangeState(state.copy(tag = it)) },
            onDeleteTag = onDeleteTag,
            onSaveTag = onSaveTag,
        )
        TimeChooser(
            time = state.time,
            onChangeTime = { onChangeState(state.copy(time = it)) },
            date = state.date,
            onChangeDate = { onChangeState(state.copy(date = it)) }
        )
        AlarmMessage(
            state = state.message,
            onChangeState = { onChangeState(state.copy(message = it)) },
        )
    }
}
