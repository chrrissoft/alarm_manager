package com.chrrissoft.alarmmanager.clock.ui

import androidx.compose.material3.SnackbarDuration.Long
import androidx.compose.material3.SnackbarResult.ActionPerformed
import androidx.lifecycle.ViewModel
import com.chrrissoft.alarmmanager.clock.entities.ClockAlarm
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent.ClockBuilderEvent.OnChangeBuilder
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent.ClockBuilderEvent.OnSaveBuilder
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent.ClockListingEvent.OnCancelAlarm
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent.ClockListingEvent.OnDeleteAlarm
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent.ClockListingEvent.OnOpenAlarm
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent.ClockListingEvent.OnScheduleAlarm
import com.chrrissoft.alarmmanager.clock.ui.ClockEvent.OnChangePage
import com.chrrissoft.alarmmanager.clock.ui.ClockState.ListingClockState
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.CancelClockAlarmUseCase
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.DeleteClockAlarmUseCase
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.GetClockAlarmsUseCase
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.SaveClockAlarmUseCase
import com.chrrissoft.alarmmanager.clock.usecases.interfaces.ScheduleClockAlarmsUseCase
import com.chrrissoft.alarmmanager.entities.ListDetailPage
import com.chrrissoft.alarmmanager.entities.ListDetailPage.Builder
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.entities.ResState.Companion.asSnackbar
import com.chrrissoft.alarmmanager.entities.ResState.Success
import com.chrrissoft.alarmmanager.ui.SnackbarData
import com.chrrissoft.alarmmanager.ui.SnackbarData.MessageType
import com.chrrissoft.alarmmanager.ui.SnackbarData.MessageType.Error
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
class ClockViewModel @Inject constructor(
    private val ScheduleClockUseCase: ScheduleClockAlarmsUseCase,
    private val SaveClockAlarmUseCase: SaveClockAlarmUseCase,
    private val GetClockAlarmsUseCase: GetClockAlarmsUseCase,
    private val CancelClockAlarmUseCase: CancelClockAlarmUseCase,
    private val DeleteClockAlarmUseCase: DeleteClockAlarmUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(ClockState())
    val stateFlow: StateFlow<ClockState> = _state

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
        scope.launch(exceptionHandler) {
            GetClockAlarmsUseCase().collect {
                updateState(listing = _listing.copy(builders = it))
            }
        }
    }

    fun handleEvent(event: ClockEvent) {
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

    private fun collectSave(data: List<ClockAlarm>, block: suspend (ResState<*>) -> Unit) {
        scope.launch(exceptionHandler) {
            SaveClockAlarmUseCase(data).collect { block(it) }
        }
    }

    private fun collectCancel(
        data: List<ClockAlarm>,
        block: suspend (ResState<Map<ClockAlarm, ResState<*>>>) -> Unit
    ) {
        scope.launch(exceptionHandler) { CancelClockAlarmUseCase(data).collect { block(it) } }
    }

    private fun collectSchedule(
        data: List<ClockAlarm>,
        block: suspend (ResState<Map<ClockAlarm, ResState<*>>>) -> Unit
    ) {
        scope.launch(exceptionHandler) { ScheduleClockUseCase(data).collect { block(it) } }
    }

    private fun collectDelete(data: List<ClockAlarm>, block: suspend (ResState<*>) -> Unit) {
        scope.launch(exceptionHandler) {
            DeleteClockAlarmUseCase(data).collect { block(it) }
        }
    }

    private fun updateState(
        page: ListDetailPage = _page,
        snackbar: SnackbarData = _snackbar,
        builder: ResState<ClockAlarm> = _builder,
        listing: ListingClockState = _listing,
        block: (ClockState) -> Unit = {},
    ) {
        _state.update {
            block(it)
            it.copy(page = page, snackbar = snackbar, builder = builder, listing = listing)
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
