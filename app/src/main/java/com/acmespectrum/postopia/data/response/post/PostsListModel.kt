package com.example.postopia.data.response.post

data class PostsListModel(
    val message: String,
    val posts: List<Post>,
    val success: Boolean
)