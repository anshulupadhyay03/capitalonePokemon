// MainActivity.kt
package com.example.pokemonapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonapp.ui.theme.PokemonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTheme {
                PokemonApp()
            }
        }
    }
}

@Composable
fun PokemonApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "pokemon_list") {
        composable("pokemon_list") {
            PokemonListScreen(
                onPokemonClick = { name ->
                    navController.navigate("pokemon_detail/$name")
                }
            )
        }
        composable("pokemon_detail/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            PokemonDetailScreen(
                name = name,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}