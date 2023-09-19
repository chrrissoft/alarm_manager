package com.chrrissoft.alarmmanager

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import com.chrrissoft.alarmmanager.AlarmsState.Page.BUILDER
import com.chrrissoft.alarmmanager.base.AlarmBuilder.*
import com.chrrissoft.alarmmanager.Util.ui

data class AlarmsState(
    val tags: Set<String> = emptySet(),
    val oneTime: OneTimeState = OneTimeState(),
    val oneTimeIdle: OneTimeIdleState = OneTimeIdleState(),
    val windows: WindowsAlarmsState = WindowsAlarmsState(),
    val repeating: RepeatingAlarmsState = RepeatingAlarmsState(),
) {
    data class OneTimeState(
        val page: Page = BUILDER,
        val builder: OneTimeBuilder = OneTimeBuilder(),
        val listing: List<OneTimeBuilder> = emptyList(),
    )

    data class OneTimeIdleState(
        val page: Page = BUILDER,
        val builder: OneTimeIdleBuilder = OneTimeIdleBuilder(),
        val listing: List<OneTimeIdleBuilder> = emptyList(),
    )

    data class WindowsAlarmsState(
        val page: Page = BUILDER,
        val builder: WindowsBuilder = WindowsBuilder(),
        val listing: List<WindowsBuilder> = emptyList(),
    )

    data class RepeatingAlarmsState(
        val page: Page = BUILDER,
        val builder: RepeatingBuilder = RepeatingBuilder(),
        val listing: List<RepeatingBuilder> = emptyList(),
    )

    enum class Page(val icon: ImageVector) {
        BUILDER(Icons.Rounded.Build),
        LISTING(Icons.AutoMirrored.Rounded.List),
        ;

        val label = name.ui

        companion object {
            val pages = listOf(BUILDER, LISTING)
        }
    }

    enum class OperationType(val icon: ImageVector) {
        OPEN(Icons.Rounded.Favorite),
        INTENT(Icons.Rounded.Favorite),
        ;

        companion object {
            val types = listOf(OPEN, INTENT)
        }
    }
}
