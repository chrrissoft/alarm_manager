package com.chrrissoft.alarmmanager.screens.components

import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmTime(
    data: Long,
    onChangeData: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val timeState = rememberTimePickerState()
    val dateState = rememberDatePickerState()

    val calender = remember {
        Calendar.getInstance()
    }

    var timeLabel = run {
        "Now"
    }

    var dateLabel = run {
        "Now"
    }

    var showTimePicker by rememberSaveable {
        mutableStateOf(false)
    }

    var showDatePicker by rememberSaveable {
        mutableStateOf(false)
    }

    Row(modifier = modifier.fillMaxWidth()) {
        Button(
            shape = shapes.medium,
            onClick = { showTimePicker = true },
            content = {
                Row(
                    horizontalArrangement = SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = timeLabel)
                    Icon(imageVector = Icons.Rounded.Timer, contentDescription = null)
                }
            },
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.weight(.05f))

        Button(
            shape = shapes.medium,
            onClick = { showDatePicker = true },
            content = {
                Row(
                    horizontalArrangement = SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = dateLabel)
                    Icon(imageVector = Icons.Rounded.DateRange, contentDescription = null)
                }
            },
            modifier = Modifier.weight(1f)
        )
    }

    if (showTimePicker) {
        AlertDialog(
            onDismissRequest = { showTimePicker = false },
            content = { TimePicker(state = timeState) }
        )
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                Button(onClick = { showDatePicker = false }) {
                    Text(text = "Ok")
                }
            },
            content = {
                DatePicker(state = dateState)
            }
        )
    }
}
