package com.example.roomexample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_destination.*

class AddDestination : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_destination)

        saveButton.setOnClickListener {
            val reply = Intent()

            if (TextUtils.isEmpty(placeNameEditText.text) || TextUtils.isEmpty(cityNameEditText.text) || TextUtils.isEmpty(countryNameEditText.text)) {
                Toast.makeText(this, "Please enter details", Toast.LENGTH_SHORT).show()
            } else {
                val place = placeNameEditText.text.toString()
                val city = cityNameEditText.text.toString()
                val country = countryNameEditText.text.toString()

                reply.putExtra("placeName", place)
                reply.putExtra("cityName", city)
                reply.putExtra("countryName", country)

                setResult(Activity.RESULT_OK, reply)
                finish()
            }
        }
    }
}
