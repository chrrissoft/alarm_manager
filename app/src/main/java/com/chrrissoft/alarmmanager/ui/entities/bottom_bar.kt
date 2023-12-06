package com.chrrissoft.alarmmanager.ui.entities

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.chrrissoft.alarmmanager.ui.theme.navigationBarItemColors

@Composable
fun <P : Page> PagesBottomBar(
    page: P,
    pages: List<P>,
    onChangePage: (P) -> Unit,
) {
    NavigationBar(
        containerColor = colorScheme.primaryContainer,
        contentColor = colorScheme.onPrimaryContainer
    ) {
        pages.forEach {
            NavigationBarItem(
                selected = page == it,
                onClick = { onChangePage(it) },
                colors = navigationBarItemColors,
                icon = { Icon(it.icon, (null)) },
                label = { Text(text = it.label) },
            )
        }
    }
}
