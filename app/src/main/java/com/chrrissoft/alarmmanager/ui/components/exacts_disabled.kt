package com.chrrissoft.alarmmanager.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.utils.ActivityUtils.startExactsAlarmPermissionActivity

@Composable
fun ExactsAlarmsDisableCard(
    modifier: Modifier = Modifier,
) {
    val ctx = LocalContext.current as ComponentActivity
    Card(
        title = "Exacts Alarms disabled",
//        colors = CardDefaults.cardColors(colorScheme.errorContainer, colorScheme.onErrorContainer),
        modifier = modifier.padding(bottom = 10.dp),
    ) {
        Text(
            text = "Exacts Alarms disabled, so alarms marked as \"exact\" will not work, tap the button to enable this alarms types",
            style = typography.labelMedium,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { ctx.startExactsAlarmPermissionActivity() }) {
            Text(text = "Allow exacts alarms")
        }
    }
}
