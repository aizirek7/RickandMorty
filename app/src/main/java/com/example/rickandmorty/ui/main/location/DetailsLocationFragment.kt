package com.example.rickandmorty.ui.main.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Episode
import com.example.rickandmorty.data.Location
import com.example.rickandmorty.data.Utils
import com.example.rickandmorty.databinding.FragmentDetailsLocationBinding

class DetailsLocationFragment : Fragment() {
    private var _binding: FragmentDetailsLocationBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsLocationBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        val list = bundle?.getSerializable(Utils.KEY) as Location

        binding.apply {
            locationDetailsDimension.text = list.dimension
            locationDetailsName.text = list.name
            locationDetailsType.text = list.type

        }
        //видели персонажей
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}