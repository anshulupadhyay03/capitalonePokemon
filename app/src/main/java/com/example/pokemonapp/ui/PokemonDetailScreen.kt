package com.example.pokemonapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.pokemonapp.ui.viewmodel.PokemonViewModel

@Composable
fun PokemonDetailScreen(
    name: String,
    onBackClick: () -> Unit,
    viewModel: PokemonViewModel = hiltViewModel()
) {
    viewModel.loadPokemonDetail(name)
    val pokemonDetail = viewModel.pokemonDetail
    val isLoading = viewModel.isLoading
    val error = viewModel.error

    when {
        isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error: $error")
            }
        }
        pokemonDetail != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = pokemonDetail.name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                AsyncImage(
                    model = pokemonDetail.sprites.front_default,
                    contentDescription = "${pokemonDetail.name} image",
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Height: ${pokemonDetail.height}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onBackClick) {
                    Text("Back")
                }
            }
        }
    }
}