package com.example.myapplication33

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var usersReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        usersReference = FirebaseDatabase.getInstance().reference.child("users")

        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)
        val loginButton = findViewById<Button>(R.id.button2)
        val createAccountButton = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)

        textView.setOnClickListener {
            val url = "https://www.pressedintime.co.za/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }



        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                loginUser(username, password)
            } else {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            }
        }

        createAccountButton.setOnClickListener {
            // Launch the RegistrationActivity when the "create account" button is clicked
            val intent = Intent(this, register::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(username: String, password: String) {
        // Fetch user data from the database based on the entered username
        usersReference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (userSnapshot in dataSnapshot.children) {
                            val user = userSnapshot.getValue(User::class.java)
                            if (user != null && user.password == password) {
                                // Use Firebase Authentication to sign in the user
                                Toast.makeText(
                                    this@MainActivity,
                                    "Login successful",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(this@MainActivity, homepage::class.java)
                                startActivity(intent)
                                return
                            }
                        }
                        // Password does not match
                        Toast.makeText(
                            this@MainActivity,
                            "Invalid username or password",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        // Username not found in the database
                        Toast.makeText(
                            this@MainActivity,
                            "Username not found",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle database error
                    Toast.makeText(
                        this@MainActivity,
                        "Database error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }




}
