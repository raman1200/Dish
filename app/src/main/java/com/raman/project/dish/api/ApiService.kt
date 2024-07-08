package com.raman.project.dish.api


import com.raman.project.dish.model.DishData
import retrofit2.Response

import retrofit2.http.GET

interface ApiService {

    @GET("/dev/nosh-assignment")
    suspend fun getDishes(): Response<List<DishData>>
}