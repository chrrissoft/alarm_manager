package com.chrrissoft.alarmmanager.entities

import com.chrrissoft.alarmmanager.ui.SnackbarData.MessageType

sealed interface ResState<out R> {
    object Loading : ResState<Nothing>
    data class Success<R>(val data: R) : ResState<R>
    data class Error(val throwable: Throwable?) : ResState<Nothing> {
        val message get() = throwable?.message ?: "Unknown error"
    }

    companion object {
        suspend fun<T, T2> ResState<T>.map(success: suspend (T) -> T2) : ResState<T2> {
            return when (this) {
                Loading -> Loading
                is Error -> Error(throwable)
                is Success -> Success(success(data))
            }
        }

        fun ResState<*>.asSnackbar() : MessageType {
            return when (this) {
                is Error -> MessageType.Error
                Loading -> MessageType.Success
                is Success -> MessageType.Success
            }
        }
    }
}
