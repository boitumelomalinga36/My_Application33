package com.example.myapplication33

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class datePicker : AppCompatActivity() {
    private lateinit var ordersReference: DatabaseReference
    private lateinit var selectedDatesReference: DatabaseReference
    private lateinit var buttonProceedToOrderConfirmation: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_picker)

        // Initialize references
        ordersReference = FirebaseDatabase.getInstance().reference.child("orders")
        selectedDatesReference = FirebaseDatabase.getInstance().reference.child("selected_dates")

        // Initialize UI elements
        buttonProceedToOrderConfirmation = findViewById(R.id.buttonProceedToOrderConfirmation)

        // Set up the button click listener to proceed to Order Confirmation
        buttonProceedToOrderConfirmation.setOnClickListener {
            handleProceedToOrderConfirmation()
        }
    }

    private fun handleProceedToOrderConfirmation() {
        // Retrieve selected dates from CalendarView if needed
        val calendarViewDropOff = findViewById<CalendarView>(R.id.calendarView)
        val selectedDropOffDate = calendarViewDropOff.date

        val calendarViewCollect = findViewById<CalendarView>(R.id.calendarView2)
        val selectedCollectDate = calendarViewCollect.date

        // Save the selected dates to the "selected_dates" table in the database
        saveSelectedDatesToDatabase(selectedDropOffDate, selectedCollectDate)

        // Proceed to Order Confirmation page
        val intent = Intent(this, homepage::class.java)
        startActivity(intent)
    }

    private fun saveSelectedDatesToDatabase(dropOffDate: Long, collectDate: Long) {
        // Implement code to save the selected dates to the "selected_dates" table in the database
        // You can use the selectedDatesReference to push the data to the database
        // Example:
        val orderDates = OrderDates(dropOffDate, collectDate)
        val orderKey = selectedDatesReference.push().key
        if (orderKey != null) {
            selectedDatesReference.child(orderKey).setValue(orderDates)
        }
    }
}
