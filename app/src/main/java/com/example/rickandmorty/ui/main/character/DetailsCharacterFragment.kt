package com.example.rickandmorty.ui.main.character

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.rickandmorty.data.Characters
import com.example.rickandmorty.data.Utils
import com.example.rickandmorty.databinding.FragmentDetailsCharacterBinding
import com.example.rickandmorty.ui.main.episode.EpisodeViewModel

class DetailsCharacterFragment : Fragment() {
    private var _binding: FragmentDetailsCharacterBinding? = null
    private val binding get() = _binding!!
    var viewModel = EpisodeViewModel()

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

        viewModel = ViewModelProvider(this).get(EpisodeViewModel::class.java)

        val bundle = this.arguments
        val list = bundle?.getSerializable(Utils.KEY) as Characters

        binding.apply {
            characterDetailsLocation.text = list.location.name
            characterDetailsName.text = list.name
            characterDetailsOriginLocation.text = list.origin.name
            characterDetailsSpecies.text = list.species
            characterDetailsStatus.text = list.status
            context?.let {
                Glide.with(it).load(list.image).into(characterDetailsImage)
            }
            //видели в эпизодах
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

