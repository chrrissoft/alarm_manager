package com.chrrissoft.alarmmanager.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Alarm
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Repeat
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.material.icons.rounded.Window
import androidx.compose.ui.graphics.vector.ImageVector
import com.chrrissoft.alarmmanager.utils.Util.ui

enum class Screens(val route: String, val icon: ImageVector) {
    ONE_TIME("ONE_TIME", Icons.Rounded.CheckCircle),
    WINDOWS("WINDOWS", Icons.Rounded.Window),
    REPEATING("REPEATING", Icons.Rounded.Repeat),
    CLOCK("CLOCK", Icons.Rounded.Alarm),
    TAG_BUILDER("TAG_BUILDER", Icons.Rounded.Tag),
    ;

    val label = this.name.ui

    companion object {
        val screens = buildList {
            add(ONE_TIME)
            add(WINDOWS)
            add(REPEATING)
            add(CLOCK)
            add(TAG_BUILDER)
        }
    }
}