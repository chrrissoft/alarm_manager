package com.chrrissoft.alarmmanager.screens.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction.Companion.Send
import androidx.compose.ui.text.input.KeyboardCapitalization.Companion.Sentences
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.ui.components.ExpandableBuilder
import com.chrrissoft.alarmmanager.ui.theme.filledIconButtonColors
import com.chrrissoft.alarmmanager.ui.theme.textFieldColors

@Composable
fun TagBuilder(
    data: Set<String>,
    onChangeData: (Set<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    ExpandableBuilder(
        expanded = expanded,
        builder = {
            var label by remember {
                mutableStateOf("")
            }
            val add = {
                if (label.isNotEmpty() && !data.contains(label)) {
                    onChangeData(data.plus(label.trim()))
                    label = ""
                }
            }
            TextField(
                value = label,
                onValueChange = { label = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    FilledIconButton(
                        colors = filledIconButtonColors,
                        shape = shapes.medium,
                        modifier = Modifier.padding(start = 5.dp),
                        onClick = { expanded = !expanded },
                        content = {
                            val icon = if (expanded) Rounded.ExpandMore else Rounded.ExpandLess
                            Icon(imageVector = icon, contentDescription = null)
                        },
                    )
                },
                trailingIcon = {
                    FilledIconButton(
                        colors = filledIconButtonColors,
                        shape = shapes.medium,
                        modifier = Modifier.padding(end = 5.dp),
                        onClick = { add() },
                        content = {
                            Icon(imageVector = Rounded.Add, contentDescription = null)
                        },
                    )
                },
                keyboardActions = KeyboardActions { add() },
                keyboardOptions = remember {
                    KeyboardOptions(Sentences, imeAction = Send)
                },
                shape = shapes.medium,
                colors = textFieldColors,
                singleLine = true,
            )
        },
        expandedContent = {
            LazyColumn {
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                items(data.toList()) {
                    Item(
                        label = it,
                        onDelete = { onChangeData(data.minus(it)) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        },
        modifier = modifier,
    )
}

@Composable
private fun Item(
    label: String,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
            .clip(shapes.medium)
            .background(colorScheme.primaryContainer)
            .padding(10.dp)
    ) {
        Text(
            text = label,
            style = typography.labelMedium,
            color = colorScheme.onPrimaryContainer,
        )

        Icon(
            imageVector = Rounded.Cancel,
            contentDescription = null,
            tint = colorScheme.onPrimaryContainer,
            modifier = Modifier.clickable { onDelete() }
        )
    }
}
