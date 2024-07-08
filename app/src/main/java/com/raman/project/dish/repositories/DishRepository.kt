package com.raman.project.dish.repositories

import android.util.Log
import com.raman.project.dish.api.ApiService
import com.raman.project.dish.api.RetrofitClient
import com.raman.project.dish.model.DishData
import javax.inject.Inject
import retrofit2.Response

class DishRepository @Inject constructor()  {

//    suspend fun fetchDishes(): List<DishData> {
//
//        val response = RetrofitClient.getInstance().create(ApiService::class.java).getDishes()
//        if (response.isSuccessful) {
//            return response.body() ?: emptyList()
//        }
//
//        return emptyList()
//    }
    suspend fun fetchDishes(): List<DishData> {
        val response = try {
            RetrofitClient.getInstance().create(ApiService::class.java).getDishes()
        } catch (e: Exception) {
            // Handle network error (e.g., throw exception or log error)
            throw e
        }

        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            // Handle API error (e.g., log error or throw specific exception)
            Log.e("API Error", "Error fetching dishes: ${response.code()}")
            return emptyList()
        }
    }
}