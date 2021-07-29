package com.example.rickandmorty.ui.main.episode

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Characters
import com.example.rickandmorty.data.Episode
import com.example.rickandmorty.data.Location
import com.example.rickandmorty.data.Utils
import com.example.rickandmorty.databinding.FragmentDetailsEpisodeBinding
import com.example.rickandmorty.ui.main.location.DetailsLocationFragmentArgs
import java.lang.NullPointerException

class DetailsEpisodeFragment : Fragment() {
    private var _binding: FragmentDetailsEpisodeBinding? = null
    private val binding get() = _binding!!
    private val args: DetailsEpisodeFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsEpisodeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            val episode = args.episode as Episode
            binding.apply {
                episodeDetailsName.text = episode.name
                episodeDetailsEpisode.text = episode.episode
                episodeDetailsAirDate.text = episode.air_date
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