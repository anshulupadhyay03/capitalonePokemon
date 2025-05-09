package com.example.pokemonapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.pokemonapp.ui.PokemonListScreen
import com.example.pokemonapp.ui.viewmodel.PokemonViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: PokemonViewModel
    private var clickedName: String? = null

    @Before
    fun setup() {
        val fakeRepository = FakePokemonRepository()
        viewModel = PokemonViewModel(fakeRepository)
    }

    @Test
    fun pokemonListScreen_displaysNames_and_handlesClick() {
        composeTestRule.setContent {
            PokemonListScreen(
                onPokemonClick = { clickedName = it },
                viewModel = viewModel
            )
        }

        composeTestRule.onNodeWithText("Bulbasaur").assertExists()
        composeTestRule.onNodeWithText("Bulbasaur").performClick()

        assertEquals("bulbasaur", clickedName)
    }


}