package com.chrrissoft.alarmmanager.screens.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.ui.unit.IntSize

val animation = spring<IntSize>(
    stiffness = Spring.StiffnessLow,
    dampingRatio = Spring.DampingRatioLowBouncy,
)
