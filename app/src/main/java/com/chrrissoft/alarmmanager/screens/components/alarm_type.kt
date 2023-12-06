package com.chrrissoft.alarmmanager.screens.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.alarmmanager.entities.AlarmType
import com.chrrissoft.alarmmanager.entities.AlarmType.Companion.list
import com.chrrissoft.alarmmanager.ui.components.MyInputChip
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmType(
    state: AlarmType,
    onChangeState: (AlarmType) -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    val animationScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        animationScope.launch {
            listState.animateScrollBy(value = 500f, tween(delayMillis = 400))
            listState.animateScrollBy(value = -500f, tween(delayMillis = 100))
        }
    }
    LazyRow(modifier, state = listState) {
        itemsIndexed(list) { i, e ->
            MyInputChip(
                selected = state == e,
                onClick = { onChangeState(e) },
                label = { Text(text = e.label) },
            )
            if (i != list.lastIndex) Spacer(modifier = Modifier.width(5.dp))
        }
    }
}
