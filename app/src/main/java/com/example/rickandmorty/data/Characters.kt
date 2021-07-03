package com.example.rickandmorty.data

import java.io.Serializable

data class Characters(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: LocationOrder,
    val image: String,
    val episode: List<Episode>,
    val url: String,
    val created: String
) : Serializable