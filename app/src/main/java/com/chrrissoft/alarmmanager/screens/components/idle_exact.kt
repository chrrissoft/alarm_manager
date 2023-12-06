package com.chrrissoft.alarmmanager.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.tags.ui.TagsModal
import com.chrrissoft.alarmmanager.ui.components.MyInputChip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdleAndExactAndTag(
    idle: Boolean,
    exact: Boolean,
    tag: String?,
    tagEnabled: Boolean,
    onIdleChange: (Boolean) -> Unit,
    onExactChange: (Boolean) -> Unit,
    onTagChange: (String?) -> Unit,
    modifier: Modifier = Modifier,
    tags: ResState<Set<String>>,
    onDeleteTag: ((String) -> Unit)? = null,
    onSaveTag: ((String) -> Unit)? = null,
) {
    Row(modifier.fillMaxWidth()) {
        MyInputChip(
            selected = idle,
            label = { Text(text = "Idle") },
            onClick = { onIdleChange(!idle) },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.weight(.05f))
        MyInputChip(
            selected = exact,
            label = { Text(text = "Exact") },
            onClick = { onExactChange(!exact) },
            modifier = Modifier.weight(1f)
        )

        var showTags by remember { mutableStateOf(value = false) }
        LaunchedEffect(tagEnabled) { if (!tagEnabled) onTagChange(null) }
        if (showTags) {
            TagsModal(
                selected = tag,
                state = tags,
                onSelect = { onTagChange(it) },
                onDismissRequest = { showTags = false },
                onSave = onSaveTag,
                onDelete = onDeleteTag,
            )
        }
        Spacer(modifier = Modifier.weight(.05f))
        MyInputChip(
            enabled = tagEnabled,
            selected = tag != null,
            label = { Text(text = tag ?: "No tag") },
            onClick = { showTags = true },
            modifier = Modifier.weight(1f)
        )
    }
}
