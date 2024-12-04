package com.acmespectrum.postopia.data.response.user

data class UserModel(
    val message: String,
    val success: Boolean,
    val user: User
)