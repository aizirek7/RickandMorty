package com.example.rickandmorty.model

import com.example.rickandmorty.data.ResultsCharacter
import com.example.rickandmorty.data.ResultsEpisode
import com.example.rickandmorty.data.ResultsLocation
import retrofit2.Response

interface RepoImpl {
    suspend fun getCharacters(name: String, status: String, species: String, type: String, gender: String): Response<ResultsCharacter>
    suspend fun getLocations(name: String, type: String, dimension: String): Response<ResultsLocation>
    suspend fun getEpisodes(name: String, episode: String): Response<ResultsEpisode>
    suspend fun getCats(status_code: String): Response<String>


}