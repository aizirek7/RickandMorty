package com.example.rickandmorty.ui.main.location

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.Characters
import com.example.rickandmorty.data.Location
import com.example.rickandmorty.data.Utils
import com.example.rickandmorty.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class LocationViewModel: ViewModel() {

    var liveDataLocation = MutableLiveData<List<Location>>()
    private val repository = Repository()

    init {
        getLocation()
    }

    private fun getLocation(name: String = "", type: String = "", dimension: String = ""){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getLocations(name, type, dimension)
                if (response.isSuccessful){
                    Log.i(Utils.MY_TAG, response.body().toString())
                    withContext(Dispatchers.Main){
                        liveDataLocation.value = response.body()?.results
                    }
                }
            } catch (e: HttpException){
                Log.i(Utils.MY_TAG, e.message().toString())
            } catch (t: Throwable){
                Log.i(Utils.MY_TAG, t.message.toString())
            }
        }
    }

    @JvmName("getLiveDataLocation1")
    fun getLiveDataLocation(): MutableLiveData<List<Location>>{
        return liveDataLocation
    }



}