 package com.example.rickandmorty.ui.main.episode

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
import retrofit2.HttpException

 class EpisodeViewModel: ViewModel() {
    var liveDataEpisode = MutableLiveData<List<Episode>>()
    private val repository = Repository()

    init {
        getLocations()
    }


    private fun getLocations(name: String = "", episode: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getEpisodes(name, episode)
                if (response.isSuccessful){
                    val characters = response.body()
                    Log.i("MyTag",characters.toString())
                    withContext(Dispatchers.Main){
                        liveDataEpisode.value = characters!!.results
                    }
                }
            }catch (e: HttpException) {
                Log.i("MYTAG", e.message().toString())
            } catch (t: Throwable) {
                Log.i("MYTAG", t.message.toString())
            }
        }
    }

    @JvmName("getLiveDataCharacter1")
    fun getLiveDataLocation(): MutableLiveData<List<Episode>> {
        return liveDataEpisode

    }

}