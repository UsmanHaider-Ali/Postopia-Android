package com.example.postopia.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.postopia.data.api.AuthService
import com.example.postopia.data.api.RetrofitInstance
import com.example.postopia.data.repository.AuthRepository
import com.example.postopia.databinding.ActivityRegisterBinding
import com.example.postopia.domain.AuthUseCases
import com.example.postopia.utils.ApiStatus

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val authService = RetrofitInstance.buildRetrofitInstance(AuthService::class.java)
        val authRepository = AuthRepository(authService)
        val authUseCases = AuthUseCases(authRepository)
        authViewModel = AuthViewModel(authUseCases)

        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Please provide all required information",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            authViewModel.register(name, email, password)
        }

        binding.tvLoginNow.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
            finishAffinity()
        }

        authViewModel.userResult.observe(this) { result ->
            result.fold(
                onSuccess = { response ->
                    Toast.makeText(
                        this@RegisterActivity,
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    finishAffinity()
                },
                onFailure = { error ->
                    Toast.makeText(this@RegisterActivity, error.message, Toast.LENGTH_SHORT)
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
        binding.btnRegister.visibility = View.GONE
    }

    private fun hideLoading() {
        binding.pbLoading.visibility = View.GONE
        binding.btnRegister.visibility = View.VISIBLE
    }
}