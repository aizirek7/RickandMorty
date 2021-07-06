package com.example.rickandmorty.ui.main.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.data.Characters
import com.example.rickandmorty.data.Episode
import com.example.rickandmorty.data.Location
import com.example.rickandmorty.data.Utils
import com.example.rickandmorty.databinding.ItemLocationBinding

class AdapterForEpiLoc(
    private val context: Context?,
    private var listLoc: List<Location>? = null,
    private var listEpi: List<Episode>? = null,
    var boolean: Boolean,
    val listener: OnItemClickListener
) : RecyclerView.Adapter<AdapterForEpiLoc.Holder>() {


    inner class Holder(val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.locationCard.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (boolean){
                listener.onItemClickLoc(adapterPosition, listLoc!!)
            }
            else{
                listener.onItemClickEpi(adapterPosition, listEpi!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemLocationBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (boolean){
            val location = listLoc!![position]
            holder.binding.apply {
                textAirDim.text = "Dimension:"
                textEpiType.text = "Type:"
                textName.text = "Name:"
                nameEpiLoc.text = location.name
                AirDim.text = location.dimension
                EpiType.text = location.type
            }
        }
        else{
            val episode = listEpi!![position]
            holder.binding.apply {
                textName.text = "Name:"
                textEpiType.text = "Episode:"
                textAirDim.text = "Air_Date:"
                nameEpiLoc.text = episode.name
                EpiType.text = episode.episode
                AirDim.text = episode.air_date
            }
        }

    }

    override fun getItemCount(): Int {
        if (boolean){
            return listLoc!!.size
        }
        else{
            return listEpi!!.size
        }
    }

    interface OnItemClickListener {
        fun onItemClickEpi(position: Int,list: List<Episode>)
        fun onItemClickLoc(position: Int,list: List<Location>)
    }
}