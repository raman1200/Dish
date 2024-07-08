package com.raman.project.dish.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://fls8oe8xp7.execute-api.ap-south-1.amazonaws.com/"


    fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Use another converter if needed
            .build()
    }

}