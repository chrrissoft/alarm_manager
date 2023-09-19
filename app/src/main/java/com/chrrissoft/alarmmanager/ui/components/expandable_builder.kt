package com.chrrissoft.alarmmanager.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableBuilder(
    expanded: Boolean,
    modifier: Modifier = Modifier,
    builder: @Composable () -> Unit,
    expandedContent: @Composable () -> Unit,
    maxHeight: Dp = LocalConfiguration.current.screenHeightDp.div(3).dp,
) {
    val background = if (expanded) MaterialTheme.colorScheme.primaryContainer.copy((.3f)) else Color.Transparent
    val padding = if (expanded) 10.dp else 0.dp

    Column(
        modifier = modifier
            .heightIn(max = maxHeight)
            .clip(MaterialTheme.shapes.medium)
            .background(background)
            .padding(padding)
            .animateContentSize()
    ) {
        builder()
        if (expanded) {
            expandedContent()
        }
    }
}
