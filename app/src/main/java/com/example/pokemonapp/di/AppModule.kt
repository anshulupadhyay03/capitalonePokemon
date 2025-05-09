package com.example.pokemonapp.di

import com.example.pokemonapp.data.network.PokemonApi
import com.example.pokemonapp.data.network.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi {
        return PokemonService.api
    }
}