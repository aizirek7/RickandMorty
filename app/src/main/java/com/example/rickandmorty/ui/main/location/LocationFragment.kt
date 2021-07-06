package com.example.rickandmorty.ui.main.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Characters
import com.example.rickandmorty.data.Episode
import com.example.rickandmorty.data.Location
import com.example.rickandmorty.data.Utils
import com.example.rickandmorty.databinding.FragmentLocationBinding
import com.example.rickandmorty.ui.main.adapters.Adapter
import com.example.rickandmorty.ui.main.adapters.AdapterForEpiLoc
import com.example.rickandmorty.ui.main.character.CharacterFragment
import com.example.rickandmorty.ui.main.episode.DetailsEpisodeFragment
import java.util.*

class LocationFragment : Fragment(), AdapterForEpiLoc.OnItemClickListener{
    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!
    var viewModel = LocationViewModel()
    lateinit var adapter: AdapterForEpiLoc

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LocationViewModel::class.java)

        viewModel.getLiveDataLocation().observe(viewLifecycleOwner,{
            start(it, true, binding.locationsRecyclerView)
        })




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun start(list: List<Location>, boolean: Boolean, recyclerView: RecyclerView) {
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        recyclerView.setHasFixedSize(true)
        adapter = AdapterForEpiLoc(context,list, null, boolean, this)
        recyclerView.adapter = adapter
    }

    override fun onItemClickEpi(position: Int, list: List<Episode>) {
        TODO("Not yet implemented")
    }

    override fun onItemClickLoc(position: Int, list: List<Location>) {
        val bundle = Bundle()
        bundle.putSerializable(Utils.KEY, list.get(position))

        val fragment = DetailsLocationFragment()
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}