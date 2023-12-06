package com.chrrissoft.alarmmanager.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.entities.AlarmOperation
import com.chrrissoft.alarmmanager.ui.components.MyInputChip
import com.chrrissoft.alarmmanager.utils.ComposeUtils.forEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmOperation(
    state: AlarmOperation,
    onChangeState: (AlarmOperation) -> Unit,
    modifier: Modifier = Modifier,
    operations: List<AlarmOperation> = AlarmOperation.operations,
) {
    Row(modifier.fillMaxWidth()) {
        forEach(list = operations) {
            MyInputChip(
                selected = state == it,
                onClick = { onChangeState(it) },
                label = { Text(text = it.label) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}
