package com.example.pokemonapp.domain.repository

import com.example.pokemonapp.data.model.Pokemon
import com.example.pokemonapp.data.model.PokemonDetail

interface PokemonRepository {
    suspend fun getPokemonList(offset: Int): List<Pokemon>
    suspend fun getPokemonDetail(name: String): PokemonDetail
}