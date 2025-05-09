package com.example.pokemonapp

import com.example.pokemonapp.data.model.Pokemon
import com.example.pokemonapp.data.model.PokemonDetail
import com.example.pokemonapp.domain.repository.PokemonRepository

class FakePokemonRepository : PokemonRepository {
    override suspend fun getPokemonList(offset: Int): List<Pokemon> {
        return listOf(Pokemon("bulbasaur","url"))
    }

    override suspend fun getPokemonDetail(name: String): PokemonDetail {
        return PokemonDetail(name, 7, PokemonDetail.Sprites("img"))
    }
}