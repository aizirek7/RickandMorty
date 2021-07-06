package com.example.rickandmorty.ui.main.episode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Characters
import com.example.rickandmorty.data.Episode
import com.example.rickandmorty.data.Utils
import com.example.rickandmorty.databinding.FragmentDetailsEpisodeBinding

class DetailsEpisodeFragment : Fragment() {
    private var _binding: FragmentDetailsEpisodeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsEpisodeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        val list = bundle?.getSerializable(Utils.KEY) as Episode

        binding.apply {
            episodeDetailsAirDate.text = list.air_date
            episodeDetailsEpisode.text = list.episode
            episodeDetailsName.text = list.name

            }
            //видели персонажей
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}