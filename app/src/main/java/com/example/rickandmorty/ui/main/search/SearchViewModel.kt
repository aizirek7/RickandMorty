package com.example.rickandmorty.ui.main.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.Characters
import com.example.rickandmorty.data.Episode
import com.example.rickandmorty.data.Location
import com.example.rickandmorty.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel: ViewModel() {
    val liveDataFindCharacter = MutableLiveData<List<Characters>>()
    val liveDataFindLocation = MutableLiveData<List<Location>>()
    val liveDataFindEpisode = MutableLiveData<List<Episode>>()
    private val repository = Repository()


    fun findCharacter(name: String = "", status: String = "", species: String = "", type: String= "", gender: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCharacters(name, status, species, type, gender )
            if (response.isSuccessful) {
                val character = response.body()
                Log.i("MySwag", character.toString())
                withContext(Dispatchers.Main) {
                    liveDataFindCharacter.value = character!!.results
                }
            }
        }
    }

    fun findLocations(name: String = "", type: String = "", dimension: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getLocations(name, type, dimension)
            if (response.isSuccessful) {
                val location = response.body()
                Log.i("MySwag", location.toString())
                withContext(Dispatchers.Main) {
                    liveDataFindLocation.value = location!!.results
                }
            }
        }
    }

    fun findEpisodes(name: String = "", episode: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getEpisodes(name, episode)
            if (response.isSuccessful) {
                val episode = response.body()
                Log.i("MySwag", episode.toString())
                withContext(Dispatchers.Main) {
                    liveDataFindEpisode.value = episode!!.results
                }

            }
        }
    }

    @JvmName("getLiveDataFindCharacter1")
    fun getLiveDataFindCharacter(): MutableLiveData<List<Characters>>{
        return liveDataFindCharacter
    }

    @JvmName("getLiveDataFindLocation1")
    fun getLiveDataFindLocation(): MutableLiveData<List<Location>>{
        return liveDataFindLocation
    }

    @JvmName("getLiveDataFindEpisode1")
    fun getLiveDataFindEpisode(): MutableLiveData<List<Episode>>{
        return liveDataFindEpisode
    }


}