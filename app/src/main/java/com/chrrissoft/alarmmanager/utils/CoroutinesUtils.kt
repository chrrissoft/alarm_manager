package com.chrrissoft.alarmmanager.utils

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

object CoroutinesUtils {
    @Suppress("FunctionName")
    inline fun SnackbarCoroutineExceptionHandler(
        crossinline handler: (CoroutineContext, Throwable) -> Unit
    ): CoroutineExceptionHandler {
        TODO()
    }

//    fun CoroutineScope.toast(message: String, ctx: Context, duration: Int = Toast.LENGTH_SHORT) {
//        withContext(Dispatchers.Main) {
//            Toast.makeText(ctx, message, duration).show()
//        }
//    }
}