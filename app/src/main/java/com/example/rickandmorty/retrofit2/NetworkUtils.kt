package com.example.rickandmorty.retrofit2

import com.example.rickandmorty.data.ResultsCharacter
import com.example.rickandmorty.data.ResultsEpisode
import com.example.rickandmorty.data.ResultsLocation
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkUtils {

    @GET("api/character")
    suspend fun getCharacters(
        @Query("name") name: String,
        @Query("status") status: String,
        @Query("species") species: String,
        @Query("type") type: String,
        @Query("gender") gender: String
    ): Response<ResultsCharacter>

    @GET("api/location")
    suspend fun getLocations(
        @Query("name") name: String,
        @Query("type") type: String,
        @Query("dimension") dimension: String
    ): Response<ResultsLocation>

    @GET("api/episode")
    suspend fun getEpisodes(
        @Query("name") name: String,
        @Query("episode") episode: String
    ): Response<ResultsEpisode>



    @GET("api/character")
    suspend fun getCharacter(): Response<ResultsCharacter>

    @GET("api/location")
    suspend fun getLocation(): Response<ResultsLocation>

    @GET("api/episode")
    suspend fun getEpisode(): Response<ResultsEpisode>

}