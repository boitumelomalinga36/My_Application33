package com.example.myapplication33

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)


        val dryCleaningButton = findViewById<ImageButton>(R.id.imageButton7)
        val sneakerButton = findViewById<ImageButton>(R.id.imageButton10)
        val blanketButton = findViewById<ImageButton>(R.id.imageButton11)
        val logOutButton = findViewById<ImageButton>(R.id.imageButton3)
        val cart = findViewById<ImageButton>(R.id.imageButton)
        val user = findViewById<ImageButton>(R.id.imageButton2)
        val FoldButton = findViewById<ImageButton>(R.id.imageButton4)
        val IronButton = findViewById<ImageButton>(R.id.imageButton5)
        val WashButton = findViewById<ImageButton>(R.id.imageButton6)



        dryCleaningButton.setOnClickListener {
            val intent = Intent(this, DryCleaning::class.java)
            startActivity(intent)
        }

        sneakerButton.setOnClickListener {
            val intent = Intent(this, Sneakers::class.java)
            startActivity(intent)
        }

        blanketButton.setOnClickListener {
            val intent = Intent(this, UserOrdersActivity::class.java)
            startActivity(intent)
        }


        logOutButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        cart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }


        user.setOnClickListener {
            val intent = Intent(this, ADMINPAGE::class.java)
            startActivity(intent)
        }


        FoldButton.setOnClickListener {
            val intent = Intent(this, WashAndFold::class.java)
            startActivity(intent)
        }


        IronButton.setOnClickListener {
            val intent = Intent(this, Iron::class.java)
            startActivity(intent)
        }


        WashButton.setOnClickListener {
            val intent = Intent(this, WashAndIron::class.java)
            startActivity(intent)
        }

    }
}