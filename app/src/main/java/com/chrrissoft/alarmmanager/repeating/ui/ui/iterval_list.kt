package com.chrrissoft.alarmmanager.repeating.ui.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.Constants
import com.chrrissoft.alarmmanager.ui.components.MyInputChip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntervalList(
    state: Pair<String, Long>,
    onChangeState: (Pair<String, Long>) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier) {
        items(Constants.INEXACT_INTERVALS) {
            MyInputChip(
                selected = state == it,
                onClick = { onChangeState(it) },
                label = { Text(text = it.first) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
