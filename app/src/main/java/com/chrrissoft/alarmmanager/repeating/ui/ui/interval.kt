package com.chrrissoft.alarmmanager.repeating.ui.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.Constants.INEXACT_INTERVALS
import com.chrrissoft.alarmmanager.screens.components.DurationInput
import com.chrrissoft.alarmmanager.ui.components.MyTextField
import com.chrrissoft.alarmmanager.ui.theme.textFieldColors
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun RepeatingAlarmIntervals(
    time: Duration,
    exact: Boolean,
    modifier: Modifier = Modifier,
    onChangeTime: (Duration) -> Unit,
) {
    val (showIntervals, changeShowIntervals) = remember { mutableStateOf(value = false) }
    var inexactInterval by remember {
        mutableStateOf(INEXACT_INTERVALS.first())
    }

    LaunchedEffect(exact) {
        if (exact) return@LaunchedEffect
        onChangeTime(inexactInterval.second.milliseconds)
    }

    if (showIntervals) {
        IntervalModels(
            state = inexactInterval,
            onChangeState = {
                inexactInterval = it
                onChangeTime(it.second.milliseconds)
            },
            onDismissRequest = { changeShowIntervals(false) }
        )
    }

    AnimatedContent(targetState = exact, label = "Exact") {
        if (it) {
            MyTextField(
                enabled = false,
                onValueChange = {},
                trailingIcon = {
                    IconToggleButton(
                        checked = showIntervals,
                        onCheckedChange = changeShowIntervals
                    ) {
                        Icon(Icons.Rounded.Face, (null))
                    }
                },
                label = { Text(text = "Tap to chose interval") },
                colors = textFieldColors(
                    disabledLabelColor = colorScheme.onPrimaryContainer.copy(alpha = .6f),
                    disabledTextColor = colorScheme.onPrimaryContainer,
                    disabledContainerColor = colorScheme.primaryContainer,
                    disabledTrailingIconColor = colorScheme.onPrimaryContainer,
                ),
                value = inexactInterval.first,
                modifier = Modifier.clickable { changeShowIntervals(true) }
            )
        } else {
            DurationInput(
                time = time,
                label = "Repeating time",
                modifier = modifier,
                onChangeTime = { onChangeTime(it) }
            )
        }
    }

}
