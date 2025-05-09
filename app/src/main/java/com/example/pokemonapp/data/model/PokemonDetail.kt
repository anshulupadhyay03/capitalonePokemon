// PokemonDetail.kt
package com.example.pokemonapp.data.model

data class PokemonDetail(
    val name: String,
    val height: Int,
    val sprites: Sprites
) {
    data class Sprites(
        val front_default: String?
    )
}