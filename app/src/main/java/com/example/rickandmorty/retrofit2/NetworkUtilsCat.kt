package com.example.rickandmorty.retrofit2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkUtilsCat {
    @GET("api/{status_code}")
    suspend fun getCats(
        @Path("status_code") status_code: String): Response<String>
}