package com.example.retrofitexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.messaging.Adapters.MessageAdapter
import com.example.retrofitexample.DataTypes.Message
import com.example.retrofitexample.DataTypes.MessageResponse
import com.example.retrofitexample.Services.MessageService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.mocklets.com/mock67863/ "

class MainActivity : AppCompatActivity() {

    private var messageList: ArrayList<Message> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.stackFromEnd = true
        messageListRecyclerView.layoutManager = linearLayoutManager

        nameTextView.text = "Grant"

        messageSendButton.setOnClickListener {
            if (messageEditText.text.toString().isNotEmpty()) {
                messageList.add(Message(messageEditText.text.toString(), "Colm", "Grant"))
                messageEditText.text.clear()
                (messageListRecyclerView.adapter as MessageAdapter).notifyDataSetChanged()
                messageListRecyclerView.smoothScrollToPosition(messageList.size-1)
            } else {
                Toast.makeText(this, "Enter a message to send", Toast.LENGTH_SHORT).show()
            }
        }

        newMessageFromReceiver.setOnClickListener{
            if (messageEditText.text.toString().isNotEmpty()) {
                messageList.add(Message(messageEditText.text.toString(), "Grant", "Colm"))
                messageEditText.text.clear()
                (messageListRecyclerView.adapter as MessageAdapter).notifyDataSetChanged()
                messageListRecyclerView.smoothScrollToPosition(messageList.size-1)
            } else {
                Toast.makeText(this, "Enter a message to send", Toast.LENGTH_SHORT).show()
            }
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MessageService::class.java)
        val call = service.getAllMessages()

        call.enqueue(object : Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>?, response: Response<MessageResponse>?) {
                if (response?.code() == 200) {
                    val body = response.body()
                     body?.messageList?.let {
                         messageList = it
                     }
                    messageListRecyclerView.adapter = MessageAdapter(messageList, applicationContext)
                }
            }

            override fun onFailure(call: Call<MessageResponse>?, t: Throwable?) {
                Log.d("log_tag_error", t.toString())
            }
        })

    }
}
