package com.example.postopia.data.api

class Endpoints {
    companion object {
        const val API_BASE_URL = "http://192.168.100.70:3000"
        private const val API = "/api"
        private const val USER = "/users"

        const val REGISTER = "$API$USER/register"
        const val LOGIN = "$API$USER/login"

    }
}