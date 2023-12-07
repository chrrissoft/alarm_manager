package com.chrrissoft.alarmmanager.repeating.ui

import android.app.AlarmManager
import androidx.compose.material3.SnackbarDuration.Long
import androidx.compose.material3.SnackbarResult.ActionPerformed
import androidx.lifecycle.ViewModel
import com.chrrissoft.alarmmanager.entities.ListDetailPage
import com.chrrissoft.alarmmanager.entities.ListDetailPage.Builder
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Companion.asSnackbar
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.repeating.entities.RepeatingAlarm
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingEvent.OnChangePage
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingEvent.RepeatingBuilderEvent.OnChangeBuilder
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingEvent.RepeatingBuilderEvent.OnSaveBuilder
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingEvent.RepeatingListingEvent.OnCancelAlarm
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingEvent.RepeatingListingEvent.OnDeleteAlarm
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingEvent.RepeatingListingEvent.OnOpenAlarm
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingEvent.RepeatingListingEvent.OnScheduleAlarm
import com.chrrissoft.alarmmanager.repeating.ui.RepeatingState.ListingRepeatingState
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.CancelRepeatingAlarmUseCase
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.DeleteRepeatingAlarmUseCase
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.GetRepeatingAlarmsUseCase
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.SaveRepeatingAlarmUseCase
import com.chrrissoft.alarmmanager.repeating.usecases.interfaces.ScheduleRepeatingAlarmsUseCase
import com.chrrissoft.alarmmanager.ui.SnackbarData
import com.chrrissoft.alarmmanager.ui.SnackbarData.MessageType
import com.chrrissoft.alarmmanager.ui.SnackbarData.MessageType.Error
import com.chrrissoft.alarmmanager.utils.AlarmManagerUtils.exactsEnabled
import com.chrrissoft.alarmmanager.utils.AlarmUtils.countText
import com.chrrissoft.alarmmanager.utils.ComposeUtils.show
import com.chrrissoft.alarmmanager.utils.Util.text
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import androidx.lifecycle.viewModelScope as scope

@HiltViewModel
class RepeatingViewModel @Inject constructor(
    alarmManager: AlarmManager,
    private val ScheduleRepeatingUseCase: ScheduleRepeatingAlarmsUseCase,
    private val SaveRepeatingAlarmUseCase: SaveRepeatingAlarmUseCase,
    private val GetRepeatingAlarmsUseCase: GetRepeatingAlarmsUseCase,
    private val CancelRepeatingAlarmUseCase: CancelRepeatingAlarmUseCase,
    private val DeleteRepeatingAlarmUseCase: DeleteRepeatingAlarmUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(RepeatingState())
    val stateFlow: StateFlow<RepeatingState> = _state

    private val state get() = stateFlow.value
    private val _page get() = state.page
    private val _snackbar get() = state.snackbar
    private val _builder get() = state.builder
    private val _listing get() = state.listing

    private val handler = EvenHandler()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        showSnackbar(message = throwable.message ?: "Unknown error", Error)
    }

    init {
        updateState(exactsAlarmEnabled = alarmManager.exactsEnabled)
        scope.launch(exceptionHandler) {
            GetRepeatingAlarmsUseCase().collect {
                updateState(listing = _listing.copy(builders = it))
            }
        }
    }

    fun handleEvent(event: RepeatingEvent) {
        try {
            event.resolve(handler)
        } catch (e: Throwable) {
            showSnackbar(message = e.message ?: "Unknown error", type = Error)
        }
    }

    inner class EvenHandler {
        val builderHandler = BuilderEventHandler()
        val listingHandler = ListingEventHandler()

        inner class BuilderEventHandler {
            fun handleEvent(event: OnSaveBuilder) {
                collectSave(event.data) {
                    showSnackbarForResult(
                        message = it.text { "Saved alarm" },
                        type = it.asSnackbar(),
                        action = "Schedule",
                    ) { listingHandler.handleEvent(OnScheduleAlarm((event.data))) }
                }
            }

            fun handleEvent(event: OnChangeBuilder) {
                updateState(builder = Success(event.data))
            }
        }

        inner class ListingEventHandler {
            fun handleEvent(event: OnOpenAlarm) {
                updateState(page = Builder, builder = Success(event.data))
            }

            fun handleEvent(event: OnCancelAlarm) {
                collectCancel(event.data) {
                    showSnackbar(it.countText(success = "canceled alarm of"), it.asSnackbar())
                }
            }

            fun handleEvent(event: OnScheduleAlarm) {
                collectSchedule(event.data) {
                    showSnackbar(it.countText(success = "alarms scheduled of"), it.asSnackbar())
                }
            }

            fun handleEvent(event: OnDeleteAlarm) {
                collectDelete(event.data) {
                    showSnackbarForResult(
                        action = "Cancel",
                        type = it.asSnackbar(),
                        message = it.text { "Deleted alarm" },
                    ) { builderHandler.handleEvent(OnSaveBuilder(event.data)) }
                }
            }
        }

        fun handleEvent(event: OnChangePage) {
            updateState(page = event.data)
        }
    }

    private fun collectSave(data: List<RepeatingAlarm>, block: suspend (ResState<*>) -> Unit) {
        scope.launch(exceptionHandler) {
            SaveRepeatingAlarmUseCase(data).collect { block(it) }
        }
    }

    private fun collectCancel(
        data: List<RepeatingAlarm>,
        block: suspend (ResState<Map<RepeatingAlarm, ResState<*>>>) -> Unit
    ) {
        scope.launch(exceptionHandler) { CancelRepeatingAlarmUseCase(data).collect { block(it) } }
    }

    private fun collectSchedule(
        data: List<RepeatingAlarm>,
        block: suspend (ResState<Map<RepeatingAlarm, ResState<*>>>) -> Unit
    ) {
        scope.launch(exceptionHandler) { ScheduleRepeatingUseCase(data).collect { block(it) } }
    }

    private fun collectDelete(data: List<RepeatingAlarm>, block: suspend (ResState<*>) -> Unit) {
        scope.launch(exceptionHandler) {
            DeleteRepeatingAlarmUseCase(data).collect { block(it) }
        }
    }

    private fun updateState(
        page: ListDetailPage = _page,
        snackbar: SnackbarData = _snackbar,
        builder: ResState<RepeatingAlarm> = _builder,
        listing: ListingRepeatingState = _listing,
        exactsAlarmEnabled: Boolean = state.exactsAlarmEnabled,
        block: (RepeatingState) -> Unit = {},
    ) {
        _state.update {
            block(it)
            it.copy(page = page, snackbar = snackbar, builder = builder, listing = listing, exactsAlarmEnabled = exactsAlarmEnabled)
        }
    }

    private fun showSnackbar(message: String, type: MessageType) {
        updateState(snackbar = _snackbar.copy(type = type))
        _snackbar.state.show(scope, message)
    }

    private fun showSnackbarForResult(
        message: String,
        type: MessageType,
        action: String,
        dispatcher: CoroutineContext? = null,
        onSuccessDismiss: () -> Unit,
    ) {
        updateState(snackbar = _snackbar.copy(type = type))
        scope.launch {
            val res = _snackbar.state.show(message, actionLabel = action, duration = Long)
            if (res == ActionPerformed && type == MessageType.Success) {
                withContext(context = dispatcher ?: this.coroutineContext) {
                    onSuccessDismiss()
                }
            }
        }
    }
}
