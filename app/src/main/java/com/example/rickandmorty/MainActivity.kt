package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.rickandmorty.data.Characters
import com.example.rickandmorty.data.Utils
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.ui.main.character.CharacterFragment
import com.example.rickandmorty.ui.main.character.CharacterViewModel
import com.example.rickandmorty.ui.main.episode.EpisodeFragment
import com.example.rickandmorty.ui.main.location.LocationFragment
import com.example.rickandmorty.ui.main.search.SearchFragment
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var list = arrayListOf<Characters>()
    var list2 = arrayListOf<Characters>()


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CharacterViewModel().getLiveDataCharacter().observe(this, {
            list = it as ArrayList<Characters>
        })

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        setFragment(CharacterFragment())

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.characters -> setFragment(CharacterFragment())
                R.id.episodes -> setFragment(EpisodeFragment())
                R.id.locations -> setFragment(LocationFragment())
                R.id.search -> setFragment(SearchFragment())
            }
            true
        }
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}