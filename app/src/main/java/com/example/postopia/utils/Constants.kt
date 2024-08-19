package com.example.postopia.utils

import org.json.JSONObject
import retrofit2.Response

class Constants {
    companion object {
        const val CONNECTION_TIME_OUT: Long = 30
        const val READ_TIME_OUT: Long = 30
        const val WRITE_TIME_OUT: Long = 30


        fun parseError(response: Response<*>): String {
            return try {
                val errorBody = response.errorBody()?.string()
                val json = JSONObject(errorBody ?: "")
                json.optString("message", "Unknown error occurred")
            } catch (e: Exception) {
                "Error parsing error response"
            }
        }
    }
}

enum class ApiStatus {
    INITIAL,
    LOADING,
    SUCCESS,
    ERROR
}