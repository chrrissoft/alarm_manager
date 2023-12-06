package com.chrrissoft.alarmmanager.utils

import android.content.Context
import android.util.Log
import com.chrrissoft.alarmmanager.entities.ResState
import com.chrrissoft.alarmmanager.utils.ContextUtils.toast
import java.util.Locale.ROOT

object Util {
    val String.ui
        get() = replace("_", " ").lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(ROOT) else it.toString() }

    fun Any.debug(message: Any?, tag: Any = this) {
        Log.d(tag::class.java.simpleName, message.toString())
    }

    @Suppress("FunctionName")
    inline fun<R> Try(
        ctx: Context? = null,
        block: () -> R?
    ): R? {
        return try {
            block()
        } catch (e: Throwable) {
            val error = e.message ?: "Unknown Error"
            ctx?.toast(message = "Error $error")
            null
        }
    }

    fun <T> ResState<T>.text(
        loading: () -> String = { "Loading" },
        error: (Throwable?) -> String = { it?.message ?: "Unknown error" },
        success: (T) -> String,
    ): String {
        return when (this) {
            ResState.Loading -> loading()
            is ResState.Error -> error(throwable)
            is ResState.Success -> success(data)
        }
    }

    suspend fun<T> ResState<T>.onSuccess(block: suspend (T) -> Unit) {
        if (this is ResState.Success) block(data)
    }
}
