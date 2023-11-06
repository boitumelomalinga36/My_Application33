package com.example.myapplication33

import Order
import android.content.Intent
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog

class Checkout : AppCompatActivity() {
    private lateinit var cardNumberEditText: EditText
    private lateinit var expiryDateEditText: EditText
    private lateinit var cvvEditText: EditText
    private lateinit var streetAddressEditText: EditText
    private lateinit var cityEditText: EditText
    private lateinit var provinceEditText: EditText
    private lateinit var postalCodeEditText: EditText
    private lateinit var checkoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        // Initialize UI elements
        cardNumberEditText = findViewById(R.id.editTextTextPersonName3)
        expiryDateEditText = findViewById(R.id.editTextTextPersonName4)
        cvvEditText = findViewById(R.id.editTextTextPersonName5)
        streetAddressEditText = findViewById(R.id.editTextTextPersonName6)
        cityEditText = findViewById(R.id.editTextTextPersonName7)
        provinceEditText = findViewById(R.id.editTextTextPersonName8)
        postalCodeEditText = findViewById(R.id.editTextTextPersonName9)
        checkoutButton = findViewById(R.id.button8)

        // Set up the checkout button click listener
        checkoutButton.setOnClickListener {
            val intent = Intent(this, order_confirmation::class.java)
            startActivity(intent)
        }
    }

    private fun handleCheckout() {
        // Retrieve user input
        val cardNumber = cardNumberEditText.text.toString()
        val expiryDate = expiryDateEditText.text.toString()
        val cvv = cvvEditText.text.toString()
        val streetAddress = streetAddressEditText.text.toString()
        val city = cityEditText.text.toString()
        val province = provinceEditText.text.toString()
        val postalCode = postalCodeEditText.text.toString()

        // Perform validation and checkout logic
        if (isValidInput(cardNumber, expiryDate, cvv, streetAddress, city, province, postalCode)) {
            // Simulate a payment processing delay (you should replace this with actual payment processing)
            Handler().postDelayed({
                // Payment successful
                val orderDetails = "Order Details:\n\n" +
                        "Card Number: $cardNumber\n" +
                        "Expiry Date: $expiryDate\n" +
                        "CVV: $cvv\n" +
                        "Street Address: $streetAddress\n" +
                        "City: $city\n" +
                        "Province: $province\n" +
                        "Postal Code: $postalCode\n\n" +
                        "Total Price: ${calculateTotalPrice()}" // Calculate the total price here

                // Create an order confirmation dialog or start a new activity to display the order details
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Order Confirmation")
                builder.setMessage(orderDetails)
                builder.setPositiveButton("OK") { dialog, _ ->
                    // Clear the shopping cart or perform any other necessary actions
                    clearCart() // Clear the cart here
                    dialog.dismiss()
                    finish() // Finish the checkout activity
                }
                builder.show()
            }, 2000) // Simulated payment processing delay of 2 seconds (replace with actual payment processing time)
        } else {
            // Handle validation errors (e.g., display error messages to the user)
            // You can add error messages here or display them to the user.
        }
        val order = createOrder()
        val intent = Intent(this, ADMINPAGE::class.java)
        intent.putExtra("order", order)
        startActivity(intent)
    }

    private fun createOrder(): Order {
        // Retrieve user input and other necessary data
        val cardNumber = cardNumberEditText.text.toString()
        val expiryDate = expiryDateEditText.text.toString()
        val cvv = cvvEditText.text.toString()
        val streetAddress = streetAddressEditText.text.toString()
        val city = cityEditText.text.toString()
        val province = provinceEditText.text.toString()
        val postalCode = postalCodeEditText.text.toString()
        val orderNumber = generateUniqueOrderNumber() // You should implement a function to generate a unique order number
        val userName = "User's Name" // Replace with the actual user's name
        val items = mutableListOf<CartItem>() // You should add the items in the order

        // You also need to set the initial status of the order, for example, "Processing"
        val status = OrderStatus.PROCESSING

        return Order(
            orderNumber,
            items,
            calculateTotalPrice(), // Calculate the total price based on the cart contents
            userName,
            cardNumber,
            expiryDate,
            cvv,
            streetAddress,
            city,
            province,
            postalCode,
            status
        )
    }

    fun generateUniqueOrderNumber(): String {
        // Generate a unique order number using a combination of timestamp and a random number, for example:
        val timestamp = System.currentTimeMillis()
        val random = (1000..9999).random() // Generate a random 4-digit number
        return "ORDER-$timestamp-$random"
    }



    // Calculate the total price based on the cart contents
    private fun calculateTotalPrice(): Double {
        val shoppingCart = ShoppingCart() // Replace with your actual cart instance
        return shoppingCart.calculateTotalPrice()
    }

    // Clear the cart
    private fun clearCart() {
        val shoppingCart = ShoppingCart() // Replace with your actual cart instance
        shoppingCart.clearCart()
    }

    private fun isValidInput(
        cardNumber: String,
        expiryDate: String,
        cvv: String,
        streetAddress: String,
        city: String,
        province: String,
        postalCode: String
    ): Boolean {
        // Implement validation rules here (e.g., check if fields are not empty, validate card number format, etc.)
        // Return true if the input is valid, otherwise return false.
        return true
    }
}
