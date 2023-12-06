package com.chrrissoft.alarmmanager.utils

import com.chrrissoft.alarmmanager.base.Alarm
import com.chrrissoft.alarmmanager.entities.ResState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object FlowUtils {
    fun<A: Alarm> alarmsFlow(
        block: suspend FlowCollector<ResState<Map<A, ResState<*>>>>.(MutableMap<A, ResState<*>>) -> Unit
    ) : Flow<ResState<Map<A, ResState<*>>>> {
        return flow {
            emit(ResState.Loading)
            val res = mutableMapOf<A, ResState<*>>()
            block(res)
            if (res.all { it.value is ResState.Success }) emit(ResState.Success(res))
            else emit(ResState.Error(Throwable("none alarm was successfully on operation")))
        }.catch { emit(ResState.Error(it)).apply { it.printStackTrace() } }.flowOn(Dispatchers.Default)
    }

    @Suppress("FunctionName")
    fun<T> ResFlow(block: suspend FlowCollector<ResState<T>>.() -> Unit) : Flow<ResState<T>> {
        return flow {
            emit(ResState.Loading)
            block()
        }.catch { emit(ResState.Error(it)) }
    }
}
