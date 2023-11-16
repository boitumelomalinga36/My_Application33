package com.example.myapplication33

import LaundryItemAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class DryCleaning : AppCompatActivity() {
    private val laundryItems = mutableListOf<LaundryItem>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var shoppingCart: ShoppingCart
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dry_cleaning)
        databaseReference = FirebaseDatabase.getInstance().reference.child("dryClean")

        laundryItems.add(LaundryItem("Curtains", 150.0))
        laundryItems.add(LaundryItem("Dress", 320.0))
        laundryItems.add(LaundryItem("Jacket ", 125.0))
        laundryItems.add(LaundryItem("Shirts", 70.0))
        laundryItems.add(LaundryItem("Skirt ", 150.0))
        laundryItems.add(LaundryItem("Coat ", 200.0))
        laundryItems.add(LaundryItem("Jersey ", 110.0))
        laundryItems.add(LaundryItem("Wedding Gown ", 700.0))
        laundryItems.add(LaundryItem("2pce ", 155.0))
        laundryItems.add(LaundryItem("3pce ", 200.0))
        laundryItems.add(LaundryItem("Tux 2pce ", 210.0))
        laundryItems.add(LaundryItem("Tux 3pce ", 230.0))
        laundryItems.add(LaundryItem("Waistcoat ", 85.0))
        laundryItems.add(LaundryItem("Carpet ", 300.0))
        laundryItems.add(LaundryItem("Kids suit ", 110.0))
        laundryItems.add(LaundryItem("Pants ", 125.0))

        shoppingCart = ShoppingCart()

        recyclerView = findViewById(R.id.recyclerView)

        // Create CartItem instances from LaundryItem
        val cartItems = laundryItems.map { laundryItem ->
            CartItem(laundryItem.name, laundryItem.price, 0)
        }.toMutableList()

        // Set up the LaundryItemAdapter
        val adapter = LaundryItemAdapter(cartItems, shoppingCart, databaseReference)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val addToCartButton = findViewById<Button>(R.id.button7)
        addToCartButton.setOnClickListener {
            // Create an intent to open the cart activity
            val intent = Intent(this, CartActivity::class.java)

            // Pass the cart items as an ArrayList of Parcelable objects
            val cartItems = ArrayList<CartItem>() // Assuming CartItem is Parcelable
            cartItems.addAll(shoppingCart.cartItems)
            intent.putParcelableArrayListExtra("cartItems", cartItems)

            // Start the cart activity
            startActivity(intent)
        }
    }
}

