package com.example.postopia.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager private constructor(context: Context) {

    companion object {
        @Volatile
        private var INSTANCE: SharedPreferencesManager? = null

        fun getInstance(context: Context): SharedPreferencesManager {
            return INSTANCE ?: synchronized(this) {
                val instance = SharedPreferencesManager(context)
                INSTANCE = instance
                instance
            }
        }
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    // Save user details
    fun saveUserDetails(userId: String, userName: String, userEmail: String) {
        with(sharedPreferences.edit()) {
            putString("USER_ID", userId)
            putString("USER_NAME", userName)
            putString("USER_EMAIL", userEmail)
            apply()
        }
    }

    // Retrieve user details
    fun getUserDetails(): User? {
        val userId = sharedPreferences.getString("USER_ID", null)
        val userName = sharedPreferences.getString("USER_NAME", null)
        val userEmail = sharedPreferences.getString("USER_EMAIL", null)

        return if (userId != null && userName != null && userEmail != null) {
            User(userId, userName, userEmail)
        } else {
            null
        }
    }

    // Clear user details
    fun clearUserDetails() {
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }

    data class User(val id: String, val name: String, val email: String)
}
