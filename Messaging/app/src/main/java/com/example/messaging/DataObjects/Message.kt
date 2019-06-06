package com.example.messaging.DataObjects

import java.text.SimpleDateFormat
import java.util.*

data class Message (var content: String, val sender: String, val receiver: String) {

    val date = Date()
    private val formatter = SimpleDateFormat("HH:mma")
    val time: String = formatter.format(date)
    var seen = false
}