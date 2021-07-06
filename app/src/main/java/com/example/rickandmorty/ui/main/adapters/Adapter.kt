package com.example.rickandmorty.ui.main.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Characters
import com.example.rickandmorty.data.Utils
import com.example.rickandmorty.databinding.ItemCharacterBinding

class Adapter(
    private val context: Context?,
    private var list: List<Characters>,
    val listener: OnItemClickListener
) : RecyclerView.Adapter<Adapter.Holder>(), Filterable {

    lateinit var list2: List<Characters>



    inner class Holder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {


        init {

            binding.charactersCard.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onItemClick(adapterPosition, list)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val character = list[position]
        holder.binding.apply {
            Log.i(Utils.MY_TAG, "Adapter")
            charactersName.text = character.name
            charactersGender.text = character.gender
            charactersLastLocation.text = character.location.name
            charactersSpecies.text = character.species
            charactersStatus.text = character.status
            context?.let {
                Glide.with(it).load(character.image).into(imageView)
            }
        }
    }

    override fun getItemCount(): Int {
        this.list2 = listOf()
        list2 = list

        return list.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, list: List<Characters>)
    }

    override fun getFilter(): Filter {
        return filter
    }


}