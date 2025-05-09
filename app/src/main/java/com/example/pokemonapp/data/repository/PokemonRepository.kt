package com.example.pokemonapp.data.repository

import com.example.pokemonapp.data.model.Pokemon
import com.example.pokemonapp.data.model.PokemonDetail
import com.example.pokemonapp.data.network.PokemonApi
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonApi
) {
    suspend fun getPokemonList(offset: Int): List<Pokemon> {
        return api.getPokemonList(offset = offset).results
    }

    suspend fun getPokemonDetail(name: String): PokemonDetail {
        return api.getPokemonDetail(name)
    }
}