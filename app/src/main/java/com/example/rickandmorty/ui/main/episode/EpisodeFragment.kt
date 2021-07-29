package com.example.rickandmorty.ui.main.episode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Characters
import com.example.rickandmorty.data.Episode
import com.example.rickandmorty.data.Location
import com.example.rickandmorty.data.Utils
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import com.example.rickandmorty.databinding.FragmentEpisodeBinding
import com.example.rickandmorty.ui.main.adapters.Adapter
import com.example.rickandmorty.ui.main.adapters.AdapterForEpiLoc
import com.example.rickandmorty.ui.main.character.CharacterFragmentDirections
import com.example.rickandmorty.ui.main.character.CharacterViewModel
import com.example.rickandmorty.ui.main.character.DetailsCharacterFragment
import java.util.*

class EpisodeFragment : Fragment(), AdapterForEpiLoc.OnItemClickListener {

    private var _binding: FragmentEpisodeBinding? = null
    private val binding get() = _binding!!
    var viewModel = EpisodeViewModel()
    lateinit var adapter: AdapterForEpiLoc


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEpisodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(EpisodeViewModel::class.java)
        viewModel.getLiveDataLocation().observe(viewLifecycleOwner, {
            start(it, false, binding.episodeRecyclerView,)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun start(list: List<Episode>, boolean: Boolean, recyclerView: RecyclerView) {
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        recyclerView.setHasFixedSize(true)
        adapter = AdapterForEpiLoc(context,null, list, boolean, this)
        recyclerView.adapter = adapter
    }

    override fun onItemClickEpi(position: Int, list: List<Episode>) {
        val action = EpisodeFragmentDirections.actionEpisodeFragmentToDetailsEpisodeFragment(list[position])
        Navigation.findNavController(binding.root).navigate(action)
    }


    override fun onItemClickLoc(position: Int, list: List<Location>) {}
}