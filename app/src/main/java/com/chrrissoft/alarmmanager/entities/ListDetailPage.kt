package com.chrrissoft.alarmmanager.entities

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Alarm
import androidx.compose.material.icons.rounded.EditCalendar
import androidx.compose.ui.graphics.vector.ImageVector
import com.chrrissoft.alarmmanager.ui.entities.Page

enum class ListDetailPage(override val icon: ImageVector, override val label: String) : Page {
    Builder(Icons.Rounded.Alarm, "Builder"),
    Listing(Icons.Rounded.EditCalendar, "Listing"),
    ;

    companion object {
        val pages = listOf(Builder, Listing)
    }
}
