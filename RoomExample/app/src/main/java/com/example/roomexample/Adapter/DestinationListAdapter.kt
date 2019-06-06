package com.example.roomexample.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.DataType.Destination
import com.example.roomexample.DataType.DestinationViewModel
import com.example.roomexample.R
import kotlinx.android.synthetic.main.destination_list_item.view.*

class DestinationListAdapter(val context: Context): RecyclerView.Adapter<DestinationListAdapter.ViewHolder>() {
    var allDestinations: List<Destination> = ArrayList()

    private var destinationViewModel: DestinationViewModel? = null

    fun setList(list: List<Destination>) {
        Log.d("log_tag", list.toString())
        this.allDestinations = list
        notifyDataSetChanged()
    }


    fun setDestinationViewModel(model: DestinationViewModel) {
        this.destinationViewModel = model
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.destination_list_item, p0, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allDestinations.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0?.destinationPlaceNameTextView.text = allDestinations[p1].placeName
        p0?.destinationCityNameTextView.text = allDestinations[p1].cityName
        p0?.destinationCountryNameTextView.text = allDestinations[p1].countryName

        p0.itemView.setOnLongClickListener{
            destinationViewModel?.delete(allDestinations[p1])
            return@setOnLongClickListener true
        }
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val destinationPlaceNameTextView: TextView = itemView.destinationPlaceNameTextView
        val destinationCityNameTextView: TextView = itemView.destinationCityNameTextView
        val destinationCountryNameTextView: TextView = itemView.destinationCountryNameTextView
    }
}