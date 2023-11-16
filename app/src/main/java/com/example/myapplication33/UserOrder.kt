package com.example.myapplication33

// UserOrder.kt

data class UserOrder(
    val order: Order,
    var status: String = "Processing" // Default status, you may update it based on your logic
)

