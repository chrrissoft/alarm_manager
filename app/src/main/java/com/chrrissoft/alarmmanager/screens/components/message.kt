package com.chrrissoft.alarmmanager.screens.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.text.input.KeyboardCapitalization.Companion.Sentences
import com.chrrissoft.alarmmanager.ui.components.MyTextField

@Composable
fun AlarmMessage(
    state: String,
    onChangeState: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    MyTextField(
        value = state,
        singleLine = true,
        label = { Text(text = "Message") },
        onValueChange = { onChangeState(it) },
        keyboardOptions = remember { KeyboardOptions(Sentences, imeAction = Done) },
        modifier = modifier
    )
}