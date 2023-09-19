package com.chrrissoft.alarmmanager.screens.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.material3.InputChipDefaults.inputChipBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.base.AlarmOperation
import com.chrrissoft.alarmmanager.base.AlarmOperation.PendingIntentOperation
import com.chrrissoft.alarmmanager.ui.theme.inputChipColorsVariant

@Composable
fun AlarmOperationUi(
    data: AlarmOperation<*>,
    onChangeData: (AlarmOperation<*>) -> Unit,
    modifier: Modifier = Modifier,
) {
    AlarmOperationUi(
        data = data,
        onChangeData = { onChangeData(it) },
        list = AlarmOperation.list,
        modifier = modifier
    )
}


@Composable
fun AlarmIntentOperationUi(
    data: PendingIntentOperation,
    onChangeData: (PendingIntentOperation) -> Unit,
    modifier: Modifier = Modifier,
) {
    AlarmOperationUi(
        data = data,
        onChangeData = { onChangeData(it) },
        list = PendingIntentOperation.list,
        modifier = modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun<O : AlarmOperation<*>> AlarmOperationUi(
    data: O,
    onChangeData: (O) -> Unit,
    list: List<O>,
    modifier: Modifier = Modifier,
) {
    LazyRow(modifier) {
        itemsIndexed(list) { i, e ->
            InputChip(
                selected = data===e,
                onClick = { onChangeData(e) },
                label = { Text(text = e.label) },
                trailingIcon = {
                    Icon(imageVector = e.icon, contentDescription = null)
                },
                colors = inputChipColorsVariant,
                border = inputChipBorder(Color.Transparent)
            )
            if (i!=list.lastIndex) Spacer(modifier = Modifier.width(5.dp))
        }
    }
}
