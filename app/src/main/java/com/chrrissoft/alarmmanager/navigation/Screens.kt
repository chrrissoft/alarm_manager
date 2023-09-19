package com.chrrissoft.alarmmanager.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import com.chrrissoft.alarmmanager.Util.ui

enum class Screens(val route: String, val icon: ImageVector) {
    TAG_BUILDER("TAG_BUILDER", Icons.Rounded.Favorite),
    ONE_TIME("ONE_TIME", Icons.Rounded.Favorite),
    ONE_TIME_IDLE("ONE_TIME_IDLE", Icons.Rounded.Favorite),
    REPEATING("REPEATING", Icons.Rounded.Favorite),
    WINDOWS("WINDOWS", Icons.Rounded.Favorite),
    ;

    val label = this.name.ui

    companion object {
        val screens = buildList {
            add(ONE_TIME)
            add(ONE_TIME_IDLE)
            add(WINDOWS)
            add(REPEATING)
            add(TAG_BUILDER)
        }
    }
}