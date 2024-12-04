package com.example.postopia.data.api

import com.example.postopia.data.api.Endpoints.Companion.LOGIN
import com.example.postopia.data.api.Endpoints.Companion.REGISTER
import com.example.postopia.data.response.user.UserModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {

    @FormUrlEncoded
    @POST(REGISTER)
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<UserModel>

    @FormUrlEncoded
    @POST(LOGIN)
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<UserModel>
}