package com.chrrissoft.alarmmanager.repeating.ui.ui

import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.DeleteForever
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Launch
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.ui.components.Card
import com.chrrissoft.alarmmanager.ui.components.MyInputChip
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepeatingItem(
    state: RepeatingAlarm,
    onOpen: () -> Unit,
    onCancel: () -> Unit,
    onDelete: () -> Unit,
    onSchedule: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier) {
        Row(Modifier.fillMaxWidth(), SpaceBetween, CenterVertically) {
            Text(text = "Will be sound in", style = typography.titleMedium)
            Row {
                val cancel = if (state.running) Rounded.Cancel to onCancel
                else Rounded.DeleteForever to onDelete
                IconButton(onClick = { onOpen() }) { Icon(Rounded.Edit, (null)) }
                IconButton(onClick = { cancel.second() }) { Icon(cancel.first, (null)) }
                if (!state.running)
                    IconButton(
                        onClick = { onSchedule() },
                        content = { Icon(Rounded.Launch, (null)) }
                    )
            }
        }
        LazyRow {
            item {
                MyInputChip(
                    selected = true,
                    onClick = { },
                    label = { Text(text = state.type.label) },
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
            item {
                MyInputChip(
                    selected = true,
                    onClick = { },
                    label = { Text(text = state.operation.label) },
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
        }
    }
}
