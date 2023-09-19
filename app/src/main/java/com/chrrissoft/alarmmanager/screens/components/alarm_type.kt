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
import com.chrrissoft.alarmmanager.base.AlarmType
import com.chrrissoft.alarmmanager.base.AlarmType.Companion.list
import com.chrrissoft.alarmmanager.ui.theme.inputChipColorsVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmTypeUi(
    data: AlarmType,
    onChangeData: (AlarmType) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(modifier) {
        itemsIndexed(list) { i, e ->
            InputChip(
                selected = data==e,
                colors = inputChipColorsVariant,
                border = inputChipBorder(Color.Transparent),
                onClick = { onChangeData(e) },
                label = { Text(text = e.label) },
            )
            if (i!=list.lastIndex) Spacer(modifier = Modifier.width(5.dp))
        }
    }
}
