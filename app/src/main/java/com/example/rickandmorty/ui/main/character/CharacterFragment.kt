package com.example.rickandmorty.ui.main.character

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Characters
import com.example.rickandmorty.data.Utils
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import com.example.rickandmorty.ui.main.adapters.Adapter
import java.util.*
import kotlin.collections.ArrayList

class CharacterFragment : Fragment(), Adapter.OnItemClickListener {
    var viewModel = CharacterViewModel()
    lateinit var adapter: Adapter
    lateinit var recyclerView: RecyclerView

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

        viewModel.getLiveDataCharacter().observe(viewLifecycleOwner, {
            start(it, binding.characterRecyclerView)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun start(list: List<Characters>, recyclerView: RecyclerView) {
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        recyclerView.setHasFixedSize(true)
        adapter = Adapter(context, list, this)
        recyclerView.adapter = adapter
    }


    override fun onItemClick(position: Int, list: List<Characters>) {
        val bundle = Bundle()
        bundle.putSerializable(Utils.KEY, list.get(position))

        val fragment = DetailsCharacterFragment()
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}