package com.example.retrofitexample.DataTypes

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

class MessageResponse {
    @SerializedName("messageList")
    var messageList: ArrayList<Message>? = null
}

class Message {
    @SerializedName("sender")
    var sender: String? = null
    @SerializedName("receiver")
    var receiver: String? = null
    @SerializedName("time")
    var time: String? = null
    @SerializedName("date")
    var date: String? = null
    @SerializedName("content")
    var content: String? = null
    @SerializedName("seen")
    var seen: Boolean = false

    constructor(content: String, sender: String, receiver: String) {
        val now = Date()
        val timeFormatter = SimpleDateFormat("HH:mma")
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy")
        time = timeFormatter.format(now)
        date = dateFormatter.format(now)
        seen = false
        this.content = content
        this.sender = sender
        this.receiver = receiver
    }
}