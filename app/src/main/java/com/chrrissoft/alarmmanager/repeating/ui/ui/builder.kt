package com.chrrissoft.alarmmanager.repeating.ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.entities.AlarmOperation.Companion.operations
import com.chrrissoft.alarmmanager.entities.AlarmOperation.Companion.repeatingOperations
import com.chrrissoft.alarmmanager.entities.AlarmOperation.Local
import com.chrrissoft.alarmmanager.entities.AlarmOperation.Receiver
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import com.chrrissoft.alarmmanager.screens.components.AlarmMessage
import com.chrrissoft.alarmmanager.screens.components.AlarmType
import com.chrrissoft.alarmmanager.screens.components.TimeChooser
import com.chrrissoft.alarmmanager.ui.components.Card
import com.chrrissoft.alarmmanager.ui.components.MyInputChip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepeatingBuilder(
    state: RepeatingAlarm,
    onChangeState: (RepeatingAlarm) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (state.operation == Local && !state.exact) onChangeState(state.copy(operation = Receiver))

    Card(modifier) {
        AlarmType(state = state.type, onChangeState = { onChangeState(state.copy(type = it)) })
        LazyRow {
            item {
                MyInputChip(
                    selected = state.exact,
                    onClick = { onChangeState(state.copy(exact = !state.exact)) },
                    label = { Text(text = "Exact") },
                    modifier = Modifier.weight(1f)
                )
                Box(
                    Modifier
                        .padding(horizontal = 10.dp)
                        .padding(vertical = 10.dp)
                        .padding(top = 3.dp)
                        .width(1.dp)
                        .height(25.dp)
                        .background(color = colorScheme.onPrimaryContainer)
                )
            }
            items(if (state.exact) operations else repeatingOperations) {
                MyInputChip(
                    selected = state.operation == it,
                    onClick = { onChangeState(state.copy(operation = it)) },
                    label = { Text(text = it.label) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
        TimeChooser(
            time = state.time,
            onChangeTime = { onChangeState(state.copy(time = it)) },
            date = state.date,
            onChangeDate = { onChangeState(state.copy(date = it)) }
        )
        RepeatingAlarmIntervals(
            exact = state.exact,
            time = state.repeating,
            onChangeTime = { onChangeState(state.copy(repeating = it)) }
        )
        AlarmMessage(
            state = state.message,
            onChangeState = { onChangeState(state.copy(message = it)) },
        )
    }
}
