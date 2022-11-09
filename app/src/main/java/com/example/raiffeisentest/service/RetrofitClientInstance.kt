package com.example.raiffeisentest.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClientInstance {
    companion object {
        private lateinit var retrofit: Retrofit
        fun getRetrofitInstance(): Retrofit {
            val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

            if (!this::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://randomuser.me/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}