package com.example.rickandmorty.retrofit2

import com.example.rickandmorty.data.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    fun getInstance(): NetworkUtils {
        val retrofit =  Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(NetworkUtils::class.java)
    }
}