package com.example.myapplication33

import LaundryItemAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class washAndIronItems : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wash_and_iron_items)
        databaseReference = FirebaseDatabase.getInstance().reference.child("user_data")
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
        val shoppingCart = ShoppingCart() // Assuming you have a ShoppingCart class

        // Convert LaundryItem instances to CartItem instances
        val cartItems = laundryItems.map { laundryItem ->
            CartItem(laundryItem.name, laundryItem.price, 0)
        }.toMutableList()

        // Initialize the adapter with CartItem instances
        val laundryItemAdapter = LaundryItemAdapter(cartItems, shoppingCart, databaseReference)

    }
}