package com.example.pokemonapp.data.repository

import com.example.pokemonapp.data.model.Pokemon
import com.example.pokemonapp.data.model.PokemonDetail
import com.example.pokemonapp.data.model.PokemonListResponse
import com.example.pokemonapp.data.network.PokemonApi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PokemonRepositoryTest {

    private lateinit var repository: PokemonRepository
    private val api: PokemonApi = mockk()

    @Before
    fun setup() {
        repository = PokemonRepository(api)
    }

    @Test
    fun `getPokemonList returns list of pokemon`() = runBlocking {
        val mockPokemon = listOf(Pokemon("bulbasaur", "url"))
        coEvery { api.getPokemonList(offset = 0) } returns PokemonListResponse(mockPokemon)

        val result = repository.getPokemonList(0)

        assertEquals(mockPokemon, result)
    }

    @Test
    fun `getPokemonDetail returns pokemon detail`() = runBlocking {
        val mockDetail = PokemonDetail(
            name = "bulbasaur",
            height = 7,
            sprites = PokemonDetail.Sprites("image_url")
        )
        coEvery { api.getPokemonDetail("bulbasaur") } returns mockDetail

        val result = repository.getPokemonDetail("bulbasaur")

        assertEquals(mockDetail, result)
    }
}