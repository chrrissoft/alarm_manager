package com.chrrissoft.alarmmanager.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachFile
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.chrrissoft.alarmmanager.Util.ui
import com.chrrissoft.alarmmanager.base.AlarmBuilder.TimeType
import com.chrrissoft.alarmmanager.ui.theme.inputChipColorsVariant

@Composable
fun AlarmTimeChooser(
    data: TimeType,
    onChangeData: (TimeType) -> Unit,
    modifier: Modifier = Modifier
) {
    AlarmTimeChooser(
        data = data,
        onChangeData = { onChangeData(it!!) },
        types = TimeType.types,
        modifier = modifier,
    )
}

@Composable
fun NullableAlarmTimeChooser(
    data: TimeType?,
    onChangeData: (TimeType?) -> Unit,
    modifier: Modifier = Modifier
) {
    AlarmTimeChooser(
        data = data,
        onChangeData = { onChangeData(it) },
        types = TimeType.types,
        modifier = modifier,
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AlarmTimeChooser(
    data: TimeType?,
    onChangeData: (TimeType?) -> Unit,
    types: List<TimeType?>,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        types.forEachIndexed { i, e ->
            InputChip(
                selected = data==e,
                onClick = { onChangeData(e) },
                label = { Text(text = e?.name?.ui ?: "All") },
                trailingIcon = { Icon(imageVector = e?.icon ?: Icons.Rounded.AttachFile, contentDescription = null) },
                colors = inputChipColorsVariant,
                border = InputChipDefaults.inputChipBorder(Color.Transparent),
                modifier = Modifier.weight(1f)
            )
            if (i!=types.lastIndex) Spacer(modifier = Modifier.weight(.05f))
        }
    }
}
