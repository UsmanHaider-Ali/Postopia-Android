package com.example.postopia.data.response.post

data class Post(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val description: String,
    val image: String,
    val updatedAt: String,
    val user: User
)