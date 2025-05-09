package com.example.pokemonapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.model.Pokemon
import com.example.pokemonapp.data.model.PokemonDetail
import com.example.pokemonapp.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    var pokemonList by mutableStateOf<List<Pokemon>>(emptyList())
        private set
    var pokemonDetail by mutableStateOf<PokemonDetail?>(null)
        private set
    var isLoading by mutableStateOf(false)
        private set
    var error by mutableStateOf<String?>(null)
        private set

    init {
        loadPokemonList()
    }

    fun loadPokemonList(offset: Int = 0) {
        viewModelScope.launch {
            isLoading = true
            try {
                pokemonList = repository.getPokemonList(offset)
                error = null
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }

    fun loadPokemonDetail(name: String) {
        viewModelScope.launch {
            isLoading = true
            try {
                pokemonDetail = repository.getPokemonDetail(name)
                error = null
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }
}