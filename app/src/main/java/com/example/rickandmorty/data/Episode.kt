package com.example.rickandmorty.data

import java.io.Serializable

data class Episode(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<Characters>,
    val url: String,
    val created: String
) : Serializable