package com.chrrissoft.alarmmanager.tags.ui

import androidx.lifecycle.ViewModel
import com.chrrissoft.alarmmanager.utils.ComposeUtils.show
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Companion.map
import com.chrrissoft.alarmmanager.tags.db.AlarmTag
import com.chrrissoft.alarmmanager.tags.ui.AlarmTagsEvent.OnDelete
import com.chrrissoft.alarmmanager.tags.ui.AlarmTagsEvent.OnSave
import com.chrrissoft.alarmmanager.tags.usecases.interfaces.DeleteAlarmTagUseCase
import com.chrrissoft.alarmmanager.tags.usecases.interfaces.GetAlarmTagsUseCase
import com.chrrissoft.alarmmanager.tags.usecases.interfaces.SaveAlarmTagUseCase
import com.chrrissoft.alarmmanager.ui.SnackbarData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.viewModelScope as scope

@HiltViewModel
class AlarmTagsViewModel @Inject constructor(
    private val SaveAlarmTagUseCase: SaveAlarmTagUseCase,
    private val GetAlarmTagsUseCase: GetAlarmTagsUseCase,
    private val DeleteAlarmTagUseCase: DeleteAlarmTagUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(AlarmTagsState())
    val stateFlow: StateFlow<AlarmTagsState> = _state
    private val state get() = stateFlow.value
    private val _snackbar get() = state.snackbar

    private val _data get() = state.data

    init {
        scope.launch {
            GetAlarmTagsUseCase().collect { res ->
                updateState(data = res.map { tags -> tags.mapTo(mutableSetOf()) { it.id } })
            }
        }
    }

    private val handler = EvenHandler()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        updateState(snackbar = _snackbar.copy(type = SnackbarData.MessageType.Error))
        _snackbar.state.show(scope, message = throwable.message ?: "Unknown error")
    }

    fun handleEvent(event: AlarmTagsEvent) {
        try {
            event.resolve(handler)
        } catch (e: Throwable) {
            updateState(snackbar = _snackbar.copy(type = SnackbarData.MessageType.Error))
            scope.launch(exceptionHandler)
            { _snackbar.state.show(message = e.message ?: "Unknown error") }
        }
    }

    inner class EvenHandler {
        fun handleEvent(event: OnSave) {
            SaveAlarmTagUseCase(AlarmTag(event.data)).launchIn(scope)
        }

        fun handleEvent(event: OnDelete) {
            DeleteAlarmTagUseCase(AlarmTag(event.data)).launchIn(scope)
        }
    }

    private fun updateState(
        snackbar: SnackbarData = _snackbar,
        data: ResState<Set<String>> = _data,
    ) {
        _state.update { it.copy(snackbar = snackbar, data = data) }
    }
}
