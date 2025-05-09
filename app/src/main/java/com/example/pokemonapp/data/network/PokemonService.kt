package com.example.pokemonapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonService {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val api: PokemonApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }
}