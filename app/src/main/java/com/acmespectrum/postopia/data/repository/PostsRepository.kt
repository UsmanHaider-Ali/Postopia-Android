package com.acmespectrum.postopia.data.repository

import com.acmespectrum.postopia.data.api.PostsService
import com.acmespectrum.postopia.data.response.post.Post
import com.acmespectrum.postopia.data.response.post.PostsListModel
import com.acmespectrum.postopia.utils.Constants.Companion.parseError

class PostsRepository(private val postsService: PostsService) {

    suspend fun getAllPosts(): Result<PostsListModel> {
        return try {
            val response = postsService.getAllPosts()

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
            return Result.failure(e)
        }
    }

    suspend fun createNewPost(
        user: String, description: String, image: String
    ): Result<Post> {
        return try {
            val response = postsService.createNewPost(user, description, image)

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
            return Result.failure(e)
        }
    }
}