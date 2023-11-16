package com.example.myapplication33

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class UserOrdersActivity : AppCompatActivity() {

    private lateinit var userOrdersRecyclerView: RecyclerView
    private lateinit var userOrdersAdapter: UserOrdersAdapter
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_orders)

        userOrdersRecyclerView = findViewById(R.id.ordersRecyclerView)
        userOrdersAdapter = UserOrdersAdapter()
        userOrdersRecyclerView.adapter = userOrdersAdapter
        userOrdersRecyclerView.layoutManager = LinearLayoutManager(this)

        database = FirebaseDatabase.getInstance().reference.child("orders")
        retrieveUserOrdersFromFirebase()
    }

    private fun retrieveUserOrdersFromFirebase() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userOrdersList = mutableListOf<UserOrder>()

                for (orderSnapshot in dataSnapshot.children) {
                    val order = orderSnapshot.getValue(Order::class.java)
                    order?.let {
                        val status = if (it.status == OrderStatus.COMPLETED) "Completed" else "Processing"
                        userOrdersList.add(UserOrder(it, status))
                    }
                }

                userOrdersAdapter.setUserOrders(userOrdersList)
                userOrdersAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors, if any
            }
        })
    }
}
