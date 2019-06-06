package com.example.messaging.Adapters

import android.content.Context
import android.content.res.ColorStateList
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.retrofitexample.DataTypes.Message
import com.example.retrofitexample.R
import kotlinx.android.synthetic.main.message_list_item_received.view.*
import kotlinx.android.synthetic.main.message_list_item_sent.view.*

class MessageAdapter (private val items: ArrayList<Message>, private val context: Context):
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var itemView: View
        var isSent: Boolean
        if (p1 == 1) {
            itemView = LayoutInflater.from(context).inflate(R.layout.message_list_item_received, p0, false)
            isSent = true
        } else {
            itemView= LayoutInflater.from(context).inflate(R.layout.message_list_item_sent, p0, false)
            isSent = false
        }

        return ViewHolder(itemView, isSent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].sender == "Colm") {
            0
        } else {
            1
        }
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        Log.d("log_tag", "2 ${p1}")

        p0.messageItemContentTextView.text = items[p1].content
        if (items[p1].seen) {
            p0.messageItemSeenView.setImageResource(R.drawable.ic_tick)
        } else {
            p0.messageItemSeenView.setImageResource(R.drawable.ic_time)
        }
        p0.messageItemTimeView.text = items[p1].time
        if (items[p1].sender == "Colm") {
            p0.itemView.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.senderColor))
            p0.itemView.setOnClickListener{
                if (!items[p1].seen) {
                    items[p1].seen = true
                    notifyDataSetChanged()
                }
            }
        } else {
            if (!items[p1].seen) {
                items[p1].seen = true
            }
            p0.itemView.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.receiverColor))
        }
    }


    class ViewHolder(view: View, isSent: Boolean): RecyclerView.ViewHolder(view) {
        var messageItemContentTextView: TextView
        var messageItemTimeView: TextView
        var messageItemSeenView: ImageView

        init {
            if (isSent) {
                messageItemTimeView = view.messageItemTimeViewSend
                messageItemContentTextView = view.messageItemContentTextViewSend
                messageItemSeenView = view.messageItemSeenViewSend
            } else {
                messageItemTimeView = view.messageItemTimeViewReceived
                messageItemContentTextView = view.messageItemContentTextViewReceived
                messageItemSeenView = view.messageItemSeenViewReceived
            }
        }
    }
}