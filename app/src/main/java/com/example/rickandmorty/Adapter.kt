package com.example.rickandmorty

import android.content.Context
import android.renderscript.ScriptGroup
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.rickandmorty.data.Characters
import com.example.rickandmorty.data.Episode
import com.example.rickandmorty.data.Location
import com.example.rickandmorty.databinding.*
import java.util.*

class Adapter(
    var context: Context?,
    var number: Int,
    var list: List<Objects>,
    var listener: OnItemClickListener
) : RecyclerView.Adapter<Adapter.Holder>() {

    lateinit var binding1: ItemCharacterBinding
    lateinit var binding2: ItemLocationBinding
    lateinit var binding3: ItemEpisodeBinding

    inner class Holder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        override fun onClick(v: View?) {
            listener.onItemClick(adapterPosition, list)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        if (number == 1){
            binding1 = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context))
            return Holder(binding1)
        }
        if (number == 2){
            binding2 = ItemLocationBinding.inflate(LayoutInflater.from(parent.context))
            return Holder(binding2)
        }
        else if (number == 3){
            binding3 = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context))
            return Holder(binding3)
        }
        return Holder(binding1)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
       if (number == 1){
           val characters = list[position] as Characters
           binding1.apply {
               characterName.text = characters.name
               characterStatus.text = characters.status
               characterSpecies.text = characters.species
               context?.let {
                   Glide.with(it).load(characters.image).into(imageView)
               }
           }
       }
        if (number == 2){
            val locations = list[position] as Location
            binding2.apply {
                locationsName.text = locations.name
                locationType.text = locations.type
                locationDimension.text = locations.dimension
            }
        }
        else if (number == 3){
            val episodes = list[position] as Episode
            binding3.apply {
                episodeName.text = episodes.name
                episodeAirDate.text = episodes.air_date
                episode.text = episodes.episode
            }
        }
    }
}

interface OnItemClickListener {
    fun onItemClick(position: Int, movies: List<Objects>)
}




