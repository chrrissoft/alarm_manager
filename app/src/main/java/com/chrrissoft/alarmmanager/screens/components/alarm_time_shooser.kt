package com.chrrissoft.alarmmanager.screens.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.ui.components.MyTextField
import com.chrrissoft.alarmmanager.ui.theme.datePickerColors
import com.chrrissoft.alarmmanager.ui.theme.timePickerColors
import com.chrrissoft.alarmmanager.utils.DateTimeUtils.getMillis
import com.chrrissoft.alarmmanager.utils.DateTimeUtils.toLocalDate
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("NewApi")
@Composable
fun TimeChooser(
    time: LocalTime,
    onChangeTime: (LocalTime) -> Unit,
    date: LocalDate,
    onChangeDate: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showDate by remember { mutableStateOf(value = false) }
    var showTime by remember { mutableStateOf(value = false) }

    val dateState = rememberDatePickerState(LocalDateTime.of(date, time).getMillis())
    val timeState = rememberTimePickerState(time.hour, time.minute)

    if (showDate) {
        DatePickerDialog(
            confirmButton = {
                Button(
                    onClick = {
                        dateState.selectedDateMillis?.toLocalDate()?.let { onChangeDate(it) }
                        showDate = false
                    },
                    content = { Text(text = "Ok") }
                )
            },
            colors = datePickerColors,
            onDismissRequest = { showDate = false },
            content = { DatePicker(state = dateState) }
        )
    }
    if (showTime) {
        AlertDialog(
            confirmButton = {
                Button(
                    onClick = {
                        LocalTime.of(timeState.hour, timeState.minute)?.let { onChangeTime(it) }
                        showTime = false
                    },
                    content = { Text(text = "Ok") }
                )
            },
            onDismissRequest = { showTime = false },
            containerColor = colorScheme.onPrimary,
            text = { TimePicker(state = timeState, colors = timePickerColors) }
        )
    }

    Row(modifier.fillMaxWidth()) {
        MyTextField(
            value = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)),
            enabled = false,
            onValueChange = {},
            label = { Text(text = "Date") },
            modifier = Modifier
                .weight(1f)
                .clickable { showDate = true }
        )
        Spacer(modifier = Modifier.weight(.05f))
        MyTextField(
            value = time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)),
            enabled = false,
            onValueChange = {},
            label = { Text(text = "Time") },
            modifier = Modifier
                .weight(1f)
                .clickable { showTime = true }
        )
    }
}
