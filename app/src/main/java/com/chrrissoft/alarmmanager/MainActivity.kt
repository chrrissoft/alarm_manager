package com.chrrissoft.alarmmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.chrrissoft.alarmmanager.Util.setBarsColors
import com.chrrissoft.alarmmanager.navigation.Graph
import com.chrrissoft.alarmmanager.ui.components.App
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App {
                setBarsColors()
                Graph()
            }
        }
    }
}
