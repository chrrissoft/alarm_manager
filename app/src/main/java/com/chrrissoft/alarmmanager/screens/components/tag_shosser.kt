package com.chrrissoft.alarmmanager.screens.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.material3.InputChipDefaults.inputChipBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.ui.theme.inputChipColorsVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagChooser(
    selected: String?,
    onChangeSelected: (String?) -> Unit,
    data: Set<String>,
    modifier: Modifier = Modifier
) {
    val list = remember(data) {
        data.toList()
    }
    LazyRow(modifier) {
        item {
            InputChip(
                selected = selected==null,
                onClick = { onChangeSelected(null) },
                label = { Text(text = "null") },
                colors = inputChipColorsVariant,
                border = inputChipBorder(Color.Transparent),
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
        itemsIndexed(list) { i, e ->
            InputChip(
                selected = selected==e,
                onClick = { onChangeSelected(e) },
                label = { Text(text = e) },
                colors = inputChipColorsVariant,
                border = inputChipBorder(Color.Transparent),
            )
            if (i!=list.lastIndex) Spacer(modifier = Modifier.width(5.dp))
        }
    }
}
