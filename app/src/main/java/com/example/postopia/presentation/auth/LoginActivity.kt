package com.example.postopia.presentation.auth

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.postopia.data.api.AuthService
import com.example.postopia.data.api.RetrofitInstance
import com.example.postopia.data.repository.AuthRepository
import com.example.postopia.databinding.ActivityLoginBinding
import com.example.postopia.domain.AuthUseCases

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var authViewModel: AuthViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val authService = RetrofitInstance.buildRetrofitInstance(AuthService::class.java)
        val authRepository = AuthRepository(authService)
        val authUseCases = AuthUseCases(authRepository)
        authViewModel = AuthViewModel(authUseCases)


        authViewModel.login("alihaider@gmail.com", "12345678")


        authViewModel.userResult.observe(this) { result ->
            result.fold(
                onSuccess = { response ->

                    Toast.makeText(
                        this@LoginActivity,
                        response.user.name,
                        Toast.LENGTH_SHORT
                    ).show()
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
                ApiStatus.LOADING -> {}
                ApiStatus.SUCCESS -> {}
                ApiStatus.ERROR -> {}
            }
        }
    }
}