package com.example.postopia.data.api

import com.example.postopia.utils.Constants.Companion.CONNECTION_TIME_OUT
import com.example.postopia.utils.Constants.Companion.READ_TIME_OUT
import com.example.postopia.utils.Constants.Companion.WRITE_TIME_OUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient =
        OkHttpClient.Builder().connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS).addInterceptor(
                logger
            )

    private val retrofitBuilder = Retrofit.Builder().baseUrl(Endpoints.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).client(
            okHttpClient.build()
        )

    private val retrofit = retrofitBuilder.build()

    fun <T> buildRetrofitInstance(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}