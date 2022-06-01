package com.mnc.nextcharge

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sntBTN = findViewById<Button>(R.id.send_button)
        sntBTN.setOnClickListener{val intent = Intent(this, NextChargeHome::class.java).apply{}
            startActivity(intent)}
    }

    /** Called when the user taps the Send button */
    fun sendMessage(view : View) {
        //val editText = findViewById<EditText>(R.id.editText)
        // val message = editText.text.toString()
        val intent = Intent(this, NextChargeHome::class.java).apply {
            // action = Intent.ACTION_SEND
            //putExtra(EXTRA_MESSAGE, message)
            //type = "text/bold"
        }
        startActivity(intent)
    }
}

