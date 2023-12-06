package com.chrrissoft.alarmmanager.screens.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.ui.components.MyTextField
import com.chrrissoft.alarmmanager.ui.theme.timePickerColors
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("NewApi")
@Composable
fun DurationInput(
    time: Duration,
    label: String,
    modifier: Modifier = Modifier,
    onChangeTime: (Duration) -> Unit,
) {
    var showTime by remember { mutableStateOf(value = false) }

    val timeState = rememberTimePickerState(
        is24Hour = true,
        initialHour = time.inWholeHours.toInt(),
        initialMinute = time.inWholeMinutes.toInt(),
    )

    if (showTime) {
        AlertDialog(
            confirmButton = {
                Button(
                    onClick = {
                        onChangeTime((timeState.hour.hours + timeState.minute.minutes))
                        showTime = false
                    },
                    content = { Text(text = "Ok") }
                )
            },
            onDismissRequest = { showTime = false },
            containerColor = MaterialTheme.colorScheme.onPrimary,
            text = { TimeInput(timeState, colors = timePickerColors) }
        )
    }

    Row(modifier.fillMaxWidth()) {
        MyTextField(
            value = "hours: ${time.inWholeHours}",
            enabled = false,
            onValueChange = {},
            label = { Text(text = label) },
            modifier = Modifier
                .weight(1f)
                .clickable { showTime = true }
        )
        MyTextField(
            value = "minutes: ${time.minus(time.inWholeHours.hours).inWholeMinutes}",
            enabled = false,
            onValueChange = {},
            label = { Text(text = label) },
            modifier = Modifier
                .weight(1f)
                .clickable { showTime = true }
        )
    }
}
