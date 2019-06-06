package com.example.retrofitexample.Services

import com.example.retrofitexample.DataTypes.MessageResponse
import retrofit2.Call
import retrofit2.http.GET


interface MessageService {
    @GET("chatList")
    fun getAllMessages(): Call<MessageResponse>
}
