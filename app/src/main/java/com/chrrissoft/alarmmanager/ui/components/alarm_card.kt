package com.chrrissoft.alarmmanager.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.screens.components.animation
import com.chrrissoft.alarmmanager.ui.theme.cardColors

@Composable
fun AlarmCard(
    modifier: Modifier = Modifier,
    shape: Shape = CardDefaults.shape,
    colors: CardColors = cardColors,
    elevation: CardElevation = CardDefaults.cardElevation(),
    border: BorderStroke? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        shape = shape,
        colors = colors,
        border = border,
        content = {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .animateContentSize(animation)
            ) {
                content()
            }
        },
        modifier = modifier,
        elevation = elevation,
    )
}
