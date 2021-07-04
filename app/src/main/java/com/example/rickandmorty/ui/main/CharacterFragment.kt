package com.example.rickandmorty.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.Adapter
import com.example.rickandmorty.OnItemClickListener
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Characters
import com.example.rickandmorty.data.Utils
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import java.util.*


class CharacterFragment : Fragment(), OnItemClickListener {
    lateinit var adapter: Adapter
    var viewModel = CharacterViewModel()

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)

        viewModel.getLiveDataCharacter().observe(viewLifecycleOwner,{
            Log.i(Utils.MY_TAG, "statusCode")
            start(it as List<Objects>, 1, binding.characterRecyclerView)
        })





    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

   fun start(list: List<Objects>, number: Int, recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(parentFragment?.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        adapter = Adapter(context, number, list, this)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(position: Int, movies: List<Objects>) {
        TODO("Not yet implemented")
    }

}