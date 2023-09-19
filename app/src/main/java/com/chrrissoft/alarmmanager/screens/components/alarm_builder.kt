@file:Suppress("UNCHECKED_CAST")

package com.chrrissoft.alarmmanager.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.base.AlarmBuilder.*
import com.chrrissoft.alarmmanager.base.AlarmBuilder.TimeType.NOT_ALLOWED
import com.chrrissoft.alarmmanager.base.AlarmOperation.PendingIntentOperation
import com.chrrissoft.alarmmanager.base.AlarmType
import com.chrrissoft.alarmmanager.ui.components.AlarmCard

@Composable
fun <O : AlarmBuilderOpenOperation> AlarmBuilderUi(
    data: O,
    onChangeData: (O) -> Unit,
    tags: Set<String>,
    modifier: Modifier = Modifier,
) {
    AlarmBuilderUi(
        type = data.type,
        onChangeType = { onChangeData(data.copyAlarm(type = it)) },
        time = data.time,
        onChangeTime = { onChangeData(data.copyAlarm(time = it)) },
        timeType = data.timeType,
        onChangeTimeType = { onChangeData(data.copyAlarm(timeType = it)) },
        specificUi = {
            if (data is WindowsBuilder) {
                ExactRepeatingIntervalChooser(
                    data = data.window,
                    onChangeData = { onChangeData(data.copy(window = it) as O) }
                )
            }
        },
        operations = {
            if (data.operation !is PendingIntentOperation) {
                TagChooser(
                    data = tags,
                    selected = data.tag,
                    onChangeSelected = { onChangeData(data.copyAlarm(tag = it)) },
                )
            }
            AlarmOperationUi(
                data = data.operation,
                onChangeData = { onChangeData(data.copyAlarm(operation = it)) },
            )
        },
        modifier = modifier,
    )
}

@Composable
fun <O : AlarmBuilderPendingIntentOperation> AlarmBuilderUi(
    data: O,
    onChangeData: (O) -> Unit,
    modifier: Modifier = Modifier,
) {
    AlarmBuilderUi(
        type = data.type,
        onChangeType = { onChangeData(data.copyAlarm(type = it)) },
        time = data.time,
        onChangeTime = { onChangeData(data.copyAlarm(time = it)) },
        timeType = data.timeType,
        onChangeTimeType = { onChangeData(data.copyAlarm(timeType = it)) },
        specificUi = {
            if (data is RepeatingBuilder) {
                when (data.timeType) {
                    TimeType.EXACT -> {
                        ExactRepeatingIntervalChooser(
                            data = data.interval,
                            onChangeData = { onChangeData(data.copy(interval = it) as O) }
                        )
                    }
                    TimeType.INEXACT -> {
                        InexactRepeatingIntervalChooser(
                            data = data.interval,
                            onChangeData = { onChangeData(data.copy(interval = it) as O) }
                        )
                    }
                    NOT_ALLOWED -> {}
                }
            }
        },
        operations = {
            AlarmIntentOperationUi(
                data = data.operation,
                onChangeData = { onChangeData(data.copyAlarm(operation = it)) },
            )
        },
        modifier = modifier,
    )
}

@Composable
private fun AlarmBuilderUi(
    type: AlarmType,
    onChangeType: (AlarmType) -> Unit,
    timeType: TimeType,
    onChangeTimeType: (TimeType) -> Unit,
    time: Long,
    onChangeTime: (Long) -> Unit,
    operations: @Composable () -> Unit,
    specificUi: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    AlarmCard(modifier) {
        operations()
        AlarmTypeUi(data = type, onChangeData = onChangeType)
        specificUi()
        if (timeType!=NOT_ALLOWED) {
            AlarmTimeChooser(data = timeType, onChangeData = onChangeTimeType)
        }
        AlarmTime(data = time, onChangeData = onChangeTime)
    }
}
