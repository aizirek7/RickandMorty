package com.example.rickandmorty.ui.main.location

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Episode
import com.example.rickandmorty.data.Location
import com.example.rickandmorty.data.Utils
import com.example.rickandmorty.databinding.FragmentDetailsLocationBinding
import com.example.rickandmorty.ui.main.character.DetailsCharacterFragmentArgs
import java.lang.NullPointerException

class DetailsLocationFragment : Fragment() {
    private var _binding: FragmentDetailsLocationBinding? = null
    private val binding get() = _binding!!
    private val args: DetailsLocationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsLocationBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            val location = args.location as Location
            binding.apply {
                locationDetailsType.text = location.type
                locationDetailsName.text = location.name
                locationDetailsDimension.text = location.dimension
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