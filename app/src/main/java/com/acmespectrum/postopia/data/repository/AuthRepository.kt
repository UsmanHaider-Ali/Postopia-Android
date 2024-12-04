package com.acmespectrum.postopia.data.repository

import com.acmespectrum.postopia.data.api.AuthService
import com.acmespectrum.postopia.data.response.user.UserModel
import com.acmespectrum.postopia.utils.Constants.Companion.parseError

class AuthRepository(private val authService: AuthService) {

    suspend fun register(name: String, email: String, password: String): Result<UserModel> {
        return try {
            val response = authService.register(name, email, password)
            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                val errorMessage = parseError(response)
                Result.failure(Exception(errorMessage))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<UserModel> {
        return try {
            val response = authService.login(email, password)

            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Response body is null"))
                }

            } else {
                val errorMessage = parseError(response)
                Result.failure(Exception(errorMessage))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}