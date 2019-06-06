package com.example.roomexample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomexample.Adapter.DestinationListAdapter
import com.example.roomexample.DataType.Destination
import com.example.roomexample.DataType.DestinationViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var destinationViewModel: DestinationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        destinationListRecyclerView.layoutManager = LinearLayoutManager(this)
        destinationListRecyclerView.adapter = DestinationListAdapter(this)

        destinationViewModel = ViewModelProviders.of(this).get(DestinationViewModel::class.java)
        destinationViewModel.allDestinations.observe(this, Observer {
            list ->
            Log.d("log_tag", "observer called")
            Log.d("log_tag", list.toString())
            list?.let {
                (destinationListRecyclerView.adapter as DestinationListAdapter).setList(list)
                (destinationListRecyclerView.adapter as DestinationListAdapter).setDestinationViewModel(destinationViewModel)
            }
        })

        addNewDestinationFAB.setOnClickListener {
            val intent = Intent(this@MainActivity, AddDestination::class.java)
            startActivityForResult(intent, newDestinationActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newDestinationActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                val destination = Destination(it.getStringExtra("placeName"), it.getStringExtra("cityName"), it.getStringExtra("countryName"))
                Log.d("log_tag", destination.toString())

                 destinationViewModel.insert(destination)


            }
        } else {
            Toast.makeText(applicationContext, "No new destination added", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val newDestinationActivityRequestCode = 1
    }

}
