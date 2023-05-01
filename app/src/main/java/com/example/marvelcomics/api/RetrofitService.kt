package com.example.marvelcomics.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    companion object {

        private lateinit var INSTANCE: Retrofit

        private fun retrofitService(): Retrofit {
            val http = OkHttpClient.Builder()
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .client(http.build())
                    .build()
            }
            return INSTANCE
        }

        fun createPostService(): GetService {
            return retrofitService().create(GetService::class.java)
        }
    }
}