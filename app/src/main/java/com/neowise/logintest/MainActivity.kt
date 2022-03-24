package com.neowise.logintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.neowise.logintest.api.LoginService
import com.neowise.logintest.api.RetrofitFactory
import com.neowise.logintest.api.model.LoginRequest
import com.neowise.logintest.api.model.LoginResponse
import com.neowise.logintest.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var loginService: LoginService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginService = RetrofitFactory.loginService

        val buttonLogin = binding.buttonLogin
        val editTextEmail = binding.editTextEmail
        val editTextPassword = binding.editTextPassword

        buttonLogin.setOnClickListener {

            val telNumber = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (telNumber.isEmpty()) {
                editTextEmail.error = "Email required"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }
            else if (password.isEmpty()) {
                editTextPassword.error = "Password need"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }
            val request = LoginRequest("loginRequest", password, telNumber.toInt())

            loginService.login(request).enqueue(object : Callback<LoginResponse> {

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                    val res = response.body()!!

                    if (res.error) {
                        Toast.makeText(this@MainActivity, "error on login: ${res.message}" , Toast.LENGTH_LONG).show()
                    }
                    else {
                        Toast.makeText(this@MainActivity, "success login: ${res.username}" , Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error while login: $t", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}