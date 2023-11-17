package com.example.myapplication33

import CartAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication33.CartItem

class CartActivity : AppCompatActivity() {
    private lateinit var cartItems: MutableList<CartItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // Retrieve cart items from the intent as a nullable list
        cartItems = intent.getParcelableArrayListExtra<CartItem>("cartItems").orEmpty().toMutableList()

        val cartRecyclerView = findViewById<RecyclerView>(R.id.cartRecyclerView)
        val cartAdapter = CartAdapter(cartItems, this)
        cartRecyclerView.adapter = cartAdapter
        cartRecyclerView.layoutManager = LinearLayoutManager(this)
        val buttonCheckout = findViewById<Button>(R.id.buttonCheckout)

        // Calculate and display the total price and total items
        updateTotalViews()

        // In CartActivity
        buttonCheckout.setOnClickListener {
            // Calculate the total price and total items
            val (totalPrice, totalItems) = calculateTotalPriceAndItems(cartItems)

            // Create the Order object
            val order = Order(
                orderNumber = generateUniqueOrderNumber(), // Implement this function
                items = cartItems,
                totalPrice = totalPrice,
                userName = "User", // Set the user name as needed
                // Set other properties as needed
            )

            // Pass the Order object, total price, and total items to OrderConfirmation activity
            val intent = Intent(this, order_confirmation::class.java).apply {
                putExtra("order", order)
                putExtra("totalPrice", totalPrice)
                putExtra("totalItems", totalItems)
            }
            startActivity(intent)
        }
    }

    // Function to calculate the total price and total items
    fun updateTotalViews() {
        val totalPriceTextView = findViewById<TextView>(R.id.totalPriceTextView)
        val totalItemsTextView = findViewById<TextView>(R.id.totalItemsTextView)

        val (totalPrice, totalItems) = calculateTotalPriceAndItems(cartItems)
        totalPriceTextView.text = "Total: R$totalPrice"
        totalItemsTextView.text = "Total Items: $totalItems"
    }

    private fun calculateTotalPriceAndItems(cartItems: List<CartItem>): Pair<Double, Int> {
        var totalPrice = 0.0
        var totalItems = 0

        for (item in cartItems) {
            totalPrice += item.price * item.quantity
            totalItems += item.quantity
        }

        return Pair(totalPrice, totalItems)
    }

    private fun generateUniqueOrderNumber(): String {
        // Implement the logic to generate a unique order number, for example, using timestamp and a random number
        val timestamp = System.currentTimeMillis()
        val random = (1000..9999).random()
        return "ORDER-$timestamp-$random"
    }
}
