package com.example.myapplication33

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class register : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var usersReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase
        database = FirebaseDatabase.getInstance()
        usersReference = database.reference.child("users") // "users" is the name of the node in the database

        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)
        val confirmPasswordEditText = findViewById<EditText>(R.id.editTextTextPassword2)
        val firstNameEditText = findViewById<EditText>(R.id.editTextTextPersonName)
        val phoneNumberEditText = findViewById<EditText>(R.id.editTextPhone)
        val registerButton = findViewById<Button>(R.id.button2)
        val loginButton = findViewById<Button>(R.id.button)

        loginButton.setOnClickListener {
            // Launch the RegistrationActivity when the "create account" button is clicked
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()
            val firstName = firstNameEditText.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString()

            if (isValidRegistration(username, password, confirmPassword, firstName, phoneNumber)) {
                val user = User(username, password, firstName, phoneNumber)

                // Save user data to Firebase
                val userId = usersReference.push().key
                if (userId != null) {
                    usersReference.child(userId).setValue(user)
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, homepage::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Error saving user data", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Registration failed, please check your inputs.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidRegistration(username: String, password: String, confirmPassword: String, firstName: String, phoneNumber: String): Boolean {
        return !username.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty() && password == confirmPassword
    }
}
