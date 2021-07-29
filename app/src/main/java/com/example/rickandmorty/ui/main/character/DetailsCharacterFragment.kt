package com.example.rickandmorty.ui.main.character

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.rickandmorty.data.Characters
import com.example.rickandmorty.data.Utils
import com.example.rickandmorty.databinding.FragmentDetailsCharacterBinding
import com.example.rickandmorty.ui.main.episode.EpisodeViewModel
import java.lang.NullPointerException

class DetailsCharacterFragment : Fragment() {
    private var _binding: FragmentDetailsCharacterBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: DetailsCharacterViewVodel
    private val args: DetailsCharacterFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            val character = args.character as Characters
            binding.apply {
                characterDetailsStatus.text = character.status
                characterDetailsSpecies.text = character.species
                characterDetailsLocation.text = character.location.name
                characterDetailsName.text = character.name
                characterDetailsOriginLocation.text = character.origin.name
                context?.let {
                    Glide.with(it).load(character.image).into(characterDetailsImage)
                }
            }
        }
        catch (e : NullPointerException){
            Log.i(Utils.MY_TAG, e.message.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


