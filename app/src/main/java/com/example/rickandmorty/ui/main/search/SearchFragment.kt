package com.example.rickandmorty.ui.main.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Characters
import com.example.rickandmorty.data.Episode
import com.example.rickandmorty.data.Location
import com.example.rickandmorty.data.Utils
import com.example.rickandmorty.databinding.FragmentSearchBinding
import com.example.rickandmorty.ui.main.adapters.Adapter
import com.example.rickandmorty.ui.main.adapters.AdapterForEpiLoc
import com.example.rickandmorty.ui.main.character.DetailsCharacterFragment
import com.example.rickandmorty.ui.main.episode.DetailsEpisodeFragment
import com.example.rickandmorty.ui.main.location.DetailsLocationFragment

class SearchFragment : Fragment(), AdapterForEpiLoc.OnItemClickListener, Adapter.OnItemClickListener{

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterForSpinner: ArrayAdapter<String>
    private lateinit var adapter: Adapter
    private lateinit var adapterForEpiLoc: AdapterForEpiLoc
    private lateinit var viewModel: SearchViewModel
    lateinit var status: String
    lateinit var gender: String
    lateinit var dimension: String
    lateinit var type: String
    lateinit var selectedItemSt: String
    private lateinit var searchListCharacter: MutableList<Characters>
    private lateinit var searchListEpisode: MutableList<Episode>
    private lateinit var searchListLocation: MutableList<Location>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        viewModel.getLiveDataFindCharacter().observe(viewLifecycleOwner, {
            searchListCharacter = mutableListOf()
            searchListCharacter.addAll(it)
            startCharacter(searchListCharacter, binding.recyclerForSearch)
        })

        viewModel.getLiveDataFindLocation().observe(viewLifecycleOwner, {
            searchListLocation = mutableListOf()
            searchListLocation.addAll(it)
            startLocation(searchListLocation, binding.recyclerForSearch)
        })

        viewModel.getLiveDataFindEpisode().observe(viewLifecycleOwner, {
            searchListEpisode = mutableListOf()
            searchListEpisode.addAll(it)
            startEpisode(searchListEpisode, binding.recyclerForSearch)
        })


        sendSearchResponse()

        val spinnerData = mutableListOf<String>()
        spinnerData.add("Episodes")
        spinnerData.add("Locations")
        spinnerData.add("Characters")
        adapterForSpinner = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            spinnerData
        )
        adapterForSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapterForSpinner
        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedItemSt = parent.selectedItem.toString()
                if (selectedItemSt == "Episodes") {
                    Toast.makeText(context, "Add your text for searching", Toast.LENGTH_SHORT)
                        .show()
                } else if (selectedItemSt == "Characters") {
                    openAddDialog()
                } else if (selectedItemSt == "Locations") {
                    openDialogLoc()

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun openAddDialog() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Choose params")
            .setMessage("Please choose all params you need")
            .setIcon(R.drawable.ic_baseline_filter_list_24)


        val view = LayoutInflater.from(context).inflate(R.layout.diolog, null)
        dialog.setView(view)
        dialog.setPositiveButton("Choose params") { _, _ ->

            if (view.findViewById<RadioButton>(R.id.alive).isChecked) {
                status = view.findViewById<RadioButton>(R.id.alive).text.toString()
            } else if (view.findViewById<RadioButton>(R.id.dead).isChecked) {
                status = view.findViewById<RadioButton>(R.id.dead).text.toString()
            } else if (view.findViewById<RadioButton>(R.id.unknown).isChecked) {
                status = view.findViewById<RadioButton>(R.id.unknown).text.toString()
            }
            if (view.findViewById<RadioButton>(R.id.male).isChecked) {
                gender = view.findViewById<RadioButton>(R.id.male).text.toString()
            } else if (view.findViewById<RadioButton>(R.id.female).isChecked) {
                gender = view.findViewById<RadioButton>(R.id.female).text.toString()
            } else if (view.findViewById<RadioButton>(R.id.genderless).isChecked) {
                gender = view.findViewById<RadioButton>(R.id.genderless).text.toString()
            } else if (view.findViewById<RadioButton>(R.id.unknownGen).isChecked) {
                gender = view.findViewById<RadioButton>(R.id.unknownGen).text.toString()
            }


        }
        dialog.setNegativeButton("Cancel") { _, _ ->

        }
        dialog.show()
    }

    private fun openDialogLoc() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Choose params")
            .setMessage("Please choose all params you need")
            .setIcon(R.drawable.ic_baseline_filter_list_24)


        val view = LayoutInflater.from(context).inflate(R.layout.diolog_location, null)
        dialog.setView(view)
        dialog.setPositiveButton("Choose params") { _, _ ->

            if (!view.findViewById<EditText>(R.id.type_dialogue)
                    .equals("") && !view.findViewById<EditText>(R.id.dimension_dialogue).equals("")
            ) {
                type = view.findViewById<EditText>(R.id.type_dialogue).toString()
                dimension = view.findViewById<EditText>(R.id.dimension_dialogue).toString()
            }
        }
        dialog.setNegativeButton("Cancel") { _, _ ->

        }
        dialog.show()
    }

    fun startCharacter(list: List<Characters>, recyclerView: RecyclerView) {
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        recyclerView.setHasFixedSize(true)
        adapter = Adapter(context, list, this)
        recyclerView.adapter = adapter
    }

    fun startLocation(list: List<Location>, recyclerView: RecyclerView) {
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        recyclerView.setHasFixedSize(true)
        adapterForEpiLoc = AdapterForEpiLoc(context, list, null, true, this)
        recyclerView.adapter = adapterForEpiLoc
    }

    fun startEpisode(list: List<Episode>, recyclerView: RecyclerView) {
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        recyclerView.setHasFixedSize(true)
        adapterForEpiLoc = AdapterForEpiLoc(context, null, list, false, this)
        recyclerView.adapter = adapterForEpiLoc
    }





    private fun sendSearchResponse() {
        binding.searchView.setOnClickListener {
            Toast.makeText(context, "Search button clicked", Toast.LENGTH_SHORT).show()
            if (selectedItemSt == "Episodes") {
                if (binding.searchText.toString().isNotEmpty()) {
                    viewModel.findEpisodes(binding.searchText.text.toString())
                }
            }
            else if (selectedItemSt == "Characters") {
                if (binding.searchText.toString().isNotEmpty() && gender.isNotEmpty() && status.isNotEmpty()) {
                    viewModel.findCharacter(binding.searchText.text.toString())
                }
            }
            else if (selectedItemSt == "Locations") {
                if (binding.searchText.toString().isNotEmpty() && type.isNotEmpty() && dimension.isNotEmpty()) {
                    viewModel.findLocations(binding.searchText.text.toString())
                }
            }
        }
    }

    override fun onItemClick(position: Int, list: List<Characters>) {
//        val bundle = Bundle()
//        bundle.putSerializable(Utils.KEY, list[position])
//        val fragment = DetailsCharacterFragment()
//        fragment.arguments = bundle
//
//        parentFragmentManager.beginTransaction().apply {
//            replace(R.id.fragment_container, fragment)
//            addToBackStack(null)
//            commit()
//        }
    }


    override fun onItemClickEpi(position: Int, list: List<Episode>) {
//        val bundle = Bundle()
//        bundle.putSerializable(Utils.KEY, list[position])
//        val fragment = DetailsEpisodeFragment()
//        fragment.arguments = bundle
//
//        parentFragmentManager.beginTransaction().apply {
//            replace(R.id.fragment_container, fragment)
//            addToBackStack(null)
//            commit()
//        }
    }

    override fun onItemClickLoc(position: Int, list: List<Location>) {
//        val bundle = Bundle()
//        bundle.putSerializable(Utils.KEY, list[position])
//        val fragment = DetailsLocationFragment()
//        fragment.arguments = bundle
//
//        parentFragmentManager.beginTransaction().apply {
//            replace(R.id.fragment_container, fragment)
//            addToBackStack(null)
//            commit()
//        }
    }
}