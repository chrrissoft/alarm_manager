package com.chrrissoft.alarmmanager.screens.components

import android.app.AlarmManager.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Alarm
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.Util.ui
import com.chrrissoft.alarmmanager.ui.theme.inputChipColorsVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InexactRepeatingIntervalChooser(
    data: Long,
    onChangeData: (Long) -> Unit,
    list: List<Long> = run {
        listOf(INTERVAL_FIFTEEN_MINUTES,
            INTERVAL_HALF_HOUR,
            INTERVAL_HOUR,
            INTERVAL_HALF_DAY,
            INTERVAL_DAY)
    },
    modifier: Modifier = Modifier,
) {
    LazyRow(modifier) {
        itemsIndexed(list) { i, e ->
            InputChip(
                selected = data==e,
                onClick = { onChangeData(e) },
                label = {
                    val text = when (e) {
                        INTERVAL_FIFTEEN_MINUTES -> "FIFTEEN_MINUTES".ui
                        INTERVAL_HALF_HOUR -> "HALF_HOUR".ui
                        INTERVAL_HOUR -> "HOUR".ui
                        INTERVAL_HALF_DAY -> "HALF_DAY".ui
                        INTERVAL_DAY -> "DAY".ui
                        else -> throw IllegalArgumentException()
                    }
                    Text(text = text)
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Rounded.Alarm, contentDescription = null)
                },
                colors = inputChipColorsVariant,
                border = InputChipDefaults.inputChipBorder(Color.Transparent)
            )
            if (i!=list.lastIndex) Spacer(modifier = Modifier.width(5.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExactRepeatingIntervalChooser(
    data: Long,
    onChangeData: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = SpaceBetween,
    ) {
        InputChip(
            selected = true,
            onClick = {  },
            label = { Text(text = "H: 00") },
            trailingIcon = {
                Icon(imageVector = Icons.Rounded.Alarm, contentDescription = null)
            },
            colors = inputChipColorsVariant,
            border = InputChipDefaults.inputChipBorder(Color.Transparent),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.weight(.05f))
        InputChip(
            selected = true,
            onClick = {  },
            label = { Text(text = "M: 00") },
            trailingIcon = {
                Icon(imageVector = Icons.Rounded.Alarm, contentDescription = null)
            },
            colors = inputChipColorsVariant,
            border = InputChipDefaults.inputChipBorder(Color.Transparent),
            modifier = Modifier.weight(1f)
        )
    }
}
