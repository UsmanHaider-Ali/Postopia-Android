package com.example.postopia.domain

import com.example.postopia.data.repository.AuthRepository
import com.example.postopia.data.response.user.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthUseCases(private val authRepository: AuthRepository) {

    suspend fun register(name: String, email: String, password: String): Result<UserModel> =
        withContext(Dispatchers.IO) {
            authRepository.register(name, email, password)
        }

    suspend fun login(email: String, password: String): Result<UserModel> =
        withContext(Dispatchers.IO) {
            authRepository.login(email, password)
        }

}