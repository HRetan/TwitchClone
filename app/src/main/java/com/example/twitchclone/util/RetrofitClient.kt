package com.example.twitchclone.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getService() : RetrofitService {
        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.twitch.tv/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(RetrofitService::class.java)
        return service
    }
}