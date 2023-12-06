package com.chrrissoft.alarmmanager.window.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.ui.components.Page
import com.chrrissoft.alarmmanager.window.entities.WindowAlarm
import com.chrrissoft.alarmmanager.window.ui.WindowEvent.WindowBuilderEvent
import com.chrrissoft.alarmmanager.window.ui.WindowEvent.WindowBuilderEvent.OnChangeBuilder

@Composable
fun WindowBuilderPage(
    state: ResState<WindowAlarm>,
    windowEvent: (WindowBuilderEvent) -> Unit,
    tags: ResState<Set<String>>,
    modifier: Modifier = Modifier,
    onDeleteTag: ((String) -> Unit)? = null,
    onSaveTag: ((String) -> Unit)? = null,
) {
    Page(state = state, modifier = modifier) { builder ->
        WindowBuilder(
            tags = tags,
            state = builder,
            onSaveTag = onSaveTag,
            onDeleteTag = onDeleteTag,
            onChangeState = { windowEvent(OnChangeBuilder(it)) },
        )
    }
}
