package com.example.rickandmorty.retrofit2

import com.example.rickandmorty.data.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceCat {
    fun getInstance(): NetworkUtilsCat {
        val retrofit =  Retrofit.Builder()
            .baseUrl(Utils.BASE_URL_FOR_EXCEPTION)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(NetworkUtilsCat::class.java)
    }
}