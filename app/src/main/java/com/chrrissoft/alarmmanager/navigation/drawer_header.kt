package com.chrrissoft.alarmmanager.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Alarm
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DrawerHeader(
    name: String = "Alarm Manager App",
    icon: ImageVector = Icons.Rounded.Alarm
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = (null),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxSize()
        )
    }
    Text(
        text = name,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineLarge.copy(color = MaterialTheme.colorScheme.primary),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(Modifier.height(10.dp))
}
