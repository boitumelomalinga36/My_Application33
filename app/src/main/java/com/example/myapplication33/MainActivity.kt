package com.example.myapplication33

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPersonName2)
        val loginButton = findViewById<Button>(R.id.button2)
        val createAccountButton = findViewById<Button>(R.id.button)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Here, you can perform authentication logic, for example, by checking the username and password.
            // For this example, we'll simply display a toast message indicating a successful login.
            if (isValidLogin(username, password)) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                // Add code to navigate to the next screen or perform other actions after successful login.
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this, homepage::class.java)
            startActivity(intent)
        }

        createAccountButton.setOnClickListener {
            // Launch the RegistrationActivity when the "create account" button is clicked
            val intent = Intent(this, register::class.java)
            startActivity(intent)
        }
    }

    private fun isValidLogin(username: String, password: String): Boolean {
        // Implement your authentication logic here. For demonstration purposes, we'll assume a valid login.
        return username == "demo" && password == "password"
    }

}
