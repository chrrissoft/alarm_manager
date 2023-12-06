package com.chrrissoft.alarmmanager.clock.ui.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.entities.AlarmOperation.Companion.repeatingOperations
import com.chrrissoft.alarmmanager.screens.components.AlarmMessage
import com.chrrissoft.alarmmanager.screens.components.AlarmOperation
import com.chrrissoft.alarmmanager.ui.components.Card

@Composable
fun ClockBuilder(
    state: ClockAlarm,
    onChangeState: (ClockAlarm) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier) {
        Text(text = "Clock Info", style = typography.titleMedium)
        ClockInfo(
            state = state.clockInfo,
            onChangeState = { onChangeState(state.copy(clockInfo = it)) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Operation", style = typography.titleMedium)
        AlarmOperation(
            state = state.operation,
            operations = repeatingOperations,
            onChangeState = { onChangeState(state.copy(operation = it)) }
        )
        AlarmMessage(
            state = state.message,
            onChangeState = { onChangeState(state.copy(message = it)) },
        )
    }
}
