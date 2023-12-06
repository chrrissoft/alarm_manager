package com.chrrissoft.alarmmanager.utils

import android.content.Context
import android.widget.Toast
import android.widget.Toast.makeText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ContextUtils {
    fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        makeText(this, message, duration).show()
    }

    suspend fun Context.toastSuspend(message: String, duration: Int = Toast.LENGTH_SHORT) {
        withContext(Dispatchers.Main) { makeText(this@toastSuspend, message, duration).show() }
    }
}
