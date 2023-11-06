package com.example.myapplication33

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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

            // Perform registration validation and logic
            if (isValidRegistration(username, password, confirmPassword, firstName, phoneNumber)) {
                // Registration is successful, you can add code here to save user data
                // and navigate to another screen, or show a success message.
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
            } else {
                // Registration failed, show an error message or handle it accordingly.
                Toast.makeText(this, "Registration failed, please check your inputs.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidRegistration(username: String, password: String, confirmPassword: String, firstName: String, phoneNumber: String): Boolean {
        // Implement your registration validation logic here
        // For example, you can check if the fields are not empty and if the passwords match.
        return !username.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty() && password == confirmPassword
    }

    }
