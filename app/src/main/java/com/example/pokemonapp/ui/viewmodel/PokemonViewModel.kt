package com.example.pokemonapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.model.Pokemon
import com.example.pokemonapp.data.model.PokemonDetail
import com.example.pokemonapp.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class Result<out T> {
    data object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    private val _pokemonListState = MutableStateFlow<Result<List<Pokemon>>>(Result.Loading)
    val pokemonListState: StateFlow<Result<List<Pokemon>>> = _pokemonListState

    private val _pokemonDetailState = MutableStateFlow<Result<PokemonDetail>?>(null)
    val pokemonDetailState: StateFlow<Result<PokemonDetail>?> = _pokemonDetailState

    fun loadPokemonList(offset: Int = 0) {
        viewModelScope.launch {
            _pokemonListState.value = Result.Loading
            try {
                val pokemonList = repository.getPokemonList(offset)
                _pokemonListState.value = Result.Success(pokemonList)
            } catch (e: Exception) {
                _pokemonListState.value = Result.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun loadPokemonDetail(name: String) {
        viewModelScope.launch {
            _pokemonDetailState.value = Result.Loading
            try {
                val pokemonDetail = repository.getPokemonDetail(name)
                _pokemonDetailState.value = Result.Success(pokemonDetail)
            } catch (e: Exception) {
                _pokemonDetailState.value = Result.Error(e.message ?: "Unknown error")
            }
        }
    }
}