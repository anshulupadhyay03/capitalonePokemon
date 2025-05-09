package com.example.pokemonapp.di

import com.example.pokemonapp.data.network.PokemonService
import com.example.pokemonapp.data.repository.PokemonRepositoryImpl
import com.example.pokemonapp.domain.repository.PokemonRepository
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
    fun providePokemonRepo(): PokemonRepository {
        return PokemonRepositoryImpl(PokemonService.api)
    }
}