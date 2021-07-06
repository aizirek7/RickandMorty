package com.example.rickandmorty.model

import com.example.rickandmorty.data.ResultsCharacter
import com.example.rickandmorty.data.ResultsEpisode
import com.example.rickandmorty.data.ResultsLocation
import com.example.rickandmorty.retrofit2.RetrofitService
import com.example.rickandmorty.retrofit2.RetrofitServiceCat
import retrofit2.Response

class Repository: RepoImpl {

    override suspend fun getCharacters(name: String, status: String, species: String, type: String, gender: String): Response<ResultsCharacter> {
        return RetrofitService.getInstance().getCharacters(name, status, species, type, gender)
    }

    override suspend fun getLocations(name: String, type: String, dimension: String): Response<ResultsLocation> {
       return RetrofitService.getInstance().getLocations(name, type, dimension)
    }

    override suspend fun getEpisodes(name: String, episode: String): Response<ResultsEpisode> {
        return RetrofitService.getInstance().getEpisodes(name, episode)
    }

    override suspend fun getCats(status_code: String): Response<String> {
        return RetrofitServiceCat.getInstance().getCats(status_code)
    }

}