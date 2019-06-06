package com.example.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.animal_list_item.view.*

class AnimalListAdapter (val items: ArrayList<String>, val context: Context):
    RecyclerView.Adapter<AnimalListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.animal_list_item, p0, false)
        val layoutParams = itemView.layoutParams
        layoutParams.height = ((0.1 * p0.height).toInt())
        itemView.layoutParams = layoutParams

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0?.listItemNameTextView?.text = items[p1].capitalize()
        p0?.listItemSerialNumberTextView?.text = (p1+1).toString()
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val listItemNameTextView: TextView = view.listItemNameTextView
        val listItemSerialNumberTextView: TextView = view.listItemSerialNumberTextView
    }
}