package com.example.postopia.data.repository

import com.example.postopia.data.api.AuthService
import com.example.postopia.data.response.user.UserModel
import com.example.postopia.utils.Constants.Companion.parseError
import org.json.JSONObject
import retrofit2.Response

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