package com.chrrissoft.alarmmanager.tags.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.tags.ui.AlarmTagsEvent.OnDelete
import com.chrrissoft.alarmmanager.tags.ui.AlarmTagsEvent.OnSave
import com.chrrissoft.alarmmanager.ui.components.MyModalBottomSheet
import com.chrrissoft.alarmmanager.ui.components.MyTextField
import com.chrrissoft.alarmmanager.ui.components.Screen
import com.chrrissoft.alarmmanager.ui.theme.textFieldColors

@Composable
fun AlarmTagsScreen(
    state: AlarmTagsState,
    onEvent: (AlarmTagsEvent) -> Unit,
    onOpenDrawer: () -> Unit,
) {
    Screen(
        title = "Alarm tags",
        content = {
            TagsListing(
                selected = null,
                state = state.data,
                onSave = { onEvent(OnSave(it)) },
                onDelete = { onEvent(OnDelete(it)) },
            )
        },
        onNavigation = onOpenDrawer
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagsModal(
    selected: String?,
    state: ResState<Set<String>>,
    onSelect: (String?) -> Unit,
    modifier: Modifier = Modifier,
    onDelete: ((String) -> Unit)? = null,
    onSave: ((String) -> Unit)? = null,
    onDismissRequest: () -> Unit,
) {
    MyModalBottomSheet(
        content = {
            TagsListing(
                selected = selected,
                state = state,
                onSelect = onSelect,
                onDelete = onDelete,
                onSave = onSave,
            )
        },
        title = "Tag listing",
        onDismissRequest = onDismissRequest,
        modifier = modifier
    )
}

@Composable
fun TagsListing(
    selected: String?,
    state: ResState<Set<String>>,
    modifier: Modifier = Modifier,
    onSelect: ((String?) -> Unit)? = null,
    onDelete: ((String) -> Unit)? = null,
    onSave: ((String) -> Unit)? = null,
) {
    LazyColumn(modifier) {
        onSave?.let {
            item {
                val (tag, changeTag) = remember { mutableStateOf(value = "") }
                MyTextField(
                    value = tag,
                    onValueChange = changeTag,
                    label = { Text(text = "Add tag here") },
                    trailingIcon =
                    { IconButton(onClick = { onSave(tag) }) { Icon(Icons.Rounded.Save, (null)) } },
                    keyboardActions = KeyboardActions { onSave(tag) },
                    keyboardOptions = remember { KeyboardOptions(imeAction = ImeAction.Done) },
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
        }

        when (state) {
            is ResState.Error -> {}
            ResState.Loading -> {}
            is ResState.Success -> {
                val list = if (onSelect != null) listOf(null) + state.data.toList()
                else state.data.toList()
                items(list) { currentTag ->
                    val colors = run {
                        if (currentTag == selected) textFieldColors(
                            disabledContainerColor = colorScheme.onPrimaryContainer,
                            disabledTextColor = colorScheme.primaryContainer,
                            disabledTrailingIconColor = colorScheme.primaryContainer
                        )
                        else textFieldColors()
                    }
                    MyTextField(
                        enabled = false,
                        colors = colors,
                        onValueChange = {},
                        value = currentTag ?: "No tag",
                        trailingIcon = if (onDelete != null && currentTag != null) {
                            {
                                IconButton(onClick = { onDelete(currentTag) }) {
                                    Icon(Icons.Rounded.Delete, (null))
                                }
                            }
                        } else null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelect?.let { it(currentTag) } }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}
