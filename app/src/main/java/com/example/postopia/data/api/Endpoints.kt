package com.example.postopia.data.api

class Endpoints {
    companion object {
        const val API_BASE_URL = "http://192.168.100.70:3000"
        private const val API = "/api"
        private const val USER = "/users"

        const val REGISTER = "$API$USER/register"
        const val LOGIN = "$API$USER/login"

        private const val POSTS = "posts"
        const val CREATE_NEW_POST = "$API$POSTS/createNewPost"
        const val GET_ALL_POSTS = "$API$POSTS/getAllPosts"


    }
}