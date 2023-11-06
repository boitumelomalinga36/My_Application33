package com.example.myapplication33

import Order
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class order_confirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)

        val order = intent.getParcelableExtra<Order>("order")

        // Access the views
        val orderNumberTextView = findViewById<TextView>(R.id.orderNumberTextView)
        val totalPriceTextView = findViewById<TextView>(R.id.totalPriceTextView)
        val continueShoppingButton = findViewById<Button>(R.id.continueShoppingButton)

        // Populate views with order information
        orderNumberTextView.text = "Order Number: ${order?.orderNumber}"
        totalPriceTextView.text = "Total Price: R${order?.totalPrice}"

        // You can similarly populate other views with more order details here.

        // Set up an event listener for the "Continue Shopping" button
        continueShoppingButton.setOnClickListener {
            // Handle the button click, e.g., return to the main shopping page
            val intent = Intent(this, homepage::class.java)
            startActivity(intent)
        }
    }
}
