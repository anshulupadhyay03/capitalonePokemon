// Pokemon.kt
package com.example.pokemonapp.data.model

data class Pokemon(
    val name: String,
    val url: String
)

data class PokemonListResponse(
    val results: List<Pokemon>
)