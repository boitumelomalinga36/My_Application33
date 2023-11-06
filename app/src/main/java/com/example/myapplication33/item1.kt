package com.example.myapplication33

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class item1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item1)

        val laundryItems = mutableListOf(
            LaundryItem("Curtains", 150.0),
            LaundryItem("Dress", 320.0),
            LaundryItem("Jacket ", 125.0),
            LaundryItem("Shirts", 70.0),
            LaundryItem("Skirt ", 150.0),
            LaundryItem("Coat ", 200.0),
            LaundryItem("Jersey ", 110.0),
            LaundryItem("Wedding Gown ", 700.0),
            LaundryItem("2pce ", 155.0),
            LaundryItem("3pce ", 200.0),
            LaundryItem("Tux 2pce ", 210.0),
            LaundryItem("Tux 3pce ", 230.0),
            LaundryItem("Waistcoat ", 85.0),
            LaundryItem("Carpet ", 300.0),
            LaundryItem("Kids suit ", 110.0),
            LaundryItem("Pants ", 125.0),
            // Add other laundry items here
        )

    }
}