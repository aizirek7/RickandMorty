package com.example.rickandmorty.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.Characters
import com.example.rickandmorty.data.Utils
import com.example.rickandmorty.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.*

class CharacterViewModel: ViewModel() {

    var liveDataCharacter = MutableLiveData<List<Characters>>()
    private val repository = Repository()
    var statusCode = ""

    init {
        getCharacter()
    }

    private fun getCharacters(name: String, status: String, species: String, type: String, gender: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getCharacters(name, status, species, type, gender)
                statusCode = response.code().toString()
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        liveDataCharacter.value = response.body()?.results
                    }
                }
            } catch (e: HttpException) {
                Log.i(Utils.MY_TAG, statusCode)
            } catch (t: Throwable) {
                Log.i(Utils.MY_TAG, statusCode)
            }
        }
    }

    private fun getCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getCharacter()
                statusCode = response.code().toString()
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        liveDataCharacter.value = response.body()?.results
                    }
                }
            } catch (e: HttpException) {
                Log.i(Utils.MY_TAG, statusCode)
            } catch (t: Throwable) {
                Log.i(Utils.MY_TAG, statusCode)
            }
        }
    }



    @JvmName("getLiveDataCharacter1")
    fun getLiveDataCharacter():MutableLiveData<List<Characters>>{
        return liveDataCharacter

    }
}

