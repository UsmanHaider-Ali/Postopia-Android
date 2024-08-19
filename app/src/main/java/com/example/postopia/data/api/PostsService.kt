package com.example.postopia.data.api

import com.example.postopia.data.api.Endpoints.Companion.CREATE_NEW_POST
import com.example.postopia.data.api.Endpoints.Companion.GET_ALL_POSTS
import com.example.postopia.data.response.post.Post
import com.example.postopia.data.response.post.PostsListModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface PostsService {

    @FormUrlEncoded
    @POST(CREATE_NEW_POST)
    suspend fun createNewPost(
        @Field("user") user: String,
        @Field("description") description: String,
        @Field("image") image: String
    ): Response<Post>

    @GET(GET_ALL_POSTS)
    suspend fun getAllPosts(): Response<PostsListModel>
}