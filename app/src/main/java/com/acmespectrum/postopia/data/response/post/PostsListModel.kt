package com.acmespectrum.postopia.data.response.post

data class PostsListModel(
    val message: String,
    val posts: List<Post>,
    val success: Boolean
)