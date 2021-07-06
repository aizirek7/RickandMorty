package com.example.rickandmorty.data

import java.io.Serializable

data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
) : Serializable