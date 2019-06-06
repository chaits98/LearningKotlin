package com.example.messaging

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.messaging.Adapters.MessageAdapter
import com.example.messaging.DataObjects.Message
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val messageList: ArrayList<Message> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.stackFromEnd = true
        messageListRecyclerView.layoutManager = linearLayoutManager
        messageListRecyclerView.adapter = MessageAdapter(messageList, this)

        nameTextView.text = getString(R.string.receiverName)

        messageSendButton.setOnClickListener {
            if (messageEditText.text.toString().isNotEmpty()) {
                messageList.add(Message(messageEditText.text.toString(), "primary", getString(R.string.receiverName)))
                messageEditText.text.clear()
                (messageListRecyclerView.adapter as MessageAdapter).notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Enter a message to send", Toast.LENGTH_SHORT).show()
            }
        }

        newMessageFromReceiver.setOnClickListener{
            if (messageEditText.text.toString().isNotEmpty()) {
                messageList.add(Message(messageEditText.text.toString(), getString(R.string.receiverName), "primary"))
                messageEditText.text.clear()
                (messageListRecyclerView.adapter as MessageAdapter).notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Enter a message to send", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
