package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.ui.main.CharacterFragment
import com.example.rickandmorty.ui.main.EpisodeFragment
import com.example.rickandmorty.ui.main.LocationFragment
import com.example.rickandmorty.ui.main.SearchFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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