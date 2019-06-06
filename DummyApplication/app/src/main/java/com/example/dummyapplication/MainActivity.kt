package com.example.dummyapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val textView = findViewById<TextView>(R.id.outputText)
//        val incButton = findViewById<Button>(R.id.increment)
//        val dcrButton = findViewById<Button>(R.id.decrement)

        incrementButton.setOnClickListener{
            inc(outputTextView)
        }

        decrementButton.setOnClickListener {
            dec(outputTextView)
        }

    }

    private fun inc (textView : TextView) {

        val value = textView.text.toString()
        var count = Integer.parseInt(value)
        count++
        textView.text = count.toString()
        Toast.makeText(this, "Count is $count", Toast.LENGTH_SHORT)
    }

    private fun dec (textView : TextView) {

        val value = textView.text.toString()
        var count = Integer.parseInt(value)
        count--
        textView.text = count.toString()
        Toast.makeText(this, "Count is $count", Toast.LENGTH_SHORT)
    }
}
