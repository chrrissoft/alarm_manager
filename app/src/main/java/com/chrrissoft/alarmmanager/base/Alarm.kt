package com.chrrissoft.alarmmanager.base

import com.chrrissoft.alarmmanager.entities.AlarmOperation

interface Alarm {
    val id: String
    val message: String
//    val time: String
//    val date: String
//    val type: AlarmType
//    val running: Boolean
    val operation: AlarmOperation
}
