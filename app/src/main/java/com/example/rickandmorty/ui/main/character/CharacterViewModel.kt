package com.example.rickandmorty.ui.main.character

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

class CharacterViewModel : ViewModel() {

    var liveDataCharacter = MutableLiveData<List<Characters>>()
    private val repository = Repository()

    init {
        getCharacters()
    }


    private fun getCharacters(name: String = "", status: String = "", species: String = "", type: String= "", gender: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    repository.getCharacters(name, status, species, type, gender)
                if (response.isSuccessful){
                    val characters = response.body()
                    Log.i("MyTag",characters.toString())
                    withContext(Dispatchers.Main){
                        liveDataCharacter.value = characters!!.results
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
    fun getLiveDataCharacter(): MutableLiveData<List<Characters>> {
        return liveDataCharacter

    }
}

