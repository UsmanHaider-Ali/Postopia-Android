package com.example.postopia.domain

import com.example.postopia.data.repository.PostsRepository
import com.example.postopia.data.response.post.Post
import com.example.postopia.data.response.post.PostsListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostsUseCases(private val postsRepository: PostsRepository) {
    suspend fun getAllPosts(): Result<PostsListModel> = withContext(Dispatchers.IO) {
        postsRepository.getAllPosts()
    }

    suspend fun createNewPos(user: String, description: String, image: String): Result<Post> =
        withContext(Dispatchers.IO) {
            postsRepository.createNewPost(user, description, image)
        }
}