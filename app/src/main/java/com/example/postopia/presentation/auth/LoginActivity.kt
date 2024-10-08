package com.example.postopia.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.postopia.presentation.main.MainActivity
import com.example.postopia.data.api.AuthService
import com.example.postopia.data.api.RetrofitInstance
import com.example.postopia.data.local.SharedPreferencesManager
import com.example.postopia.data.repository.AuthRepository
import com.example.postopia.databinding.ActivityLoginBinding
import com.example.postopia.domain.AuthUseCases
import com.example.postopia.utils.ApiStatus

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val authService = RetrofitInstance.buildRetrofitInstance(AuthService::class.java)
        val authRepository = AuthRepository(authService)
        val authUseCases = AuthUseCases(authRepository)
        authViewModel = AuthViewModel(authUseCases)

        sharedPreferencesManager = SharedPreferencesManager.getInstance(this)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this@LoginActivity,
                    "Please enter email and password",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            authViewModel.login(email, password)
        }

        binding.tvRegisterNow.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        authViewModel.userResult.observe(this) { result ->
            result.fold(
                onSuccess = { response ->
                    Toast.makeText(
                        this@LoginActivity,
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()

                    sharedPreferencesManager.saveUserDetails(
                        response.user._id,
                        response.user.name,
                        response.user.email
                    )

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    finishAffinity()
                },
                onFailure = { error ->
                    Toast.makeText(this@LoginActivity, error.message, Toast.LENGTH_SHORT)
                        .show()
                }
            )
        }

        authViewModel.apiStatus.observe(this) {
            when (it) {
                ApiStatus.INITIAL -> {}
                ApiStatus.LOADING -> {
                    showLoading()
                }

                ApiStatus.SUCCESS -> {
                    hideLoading()
                }

                ApiStatus.ERROR -> {
                    hideLoading()
                }
            }
        }
    }

    private fun showLoading() {
        binding.pbLoading.visibility = View.VISIBLE
        binding.btnLogin.visibility = View.GONE
    }

    private fun hideLoading() {
        binding.pbLoading.visibility = View.GONE
        binding.btnLogin.visibility = View.VISIBLE
    }
}