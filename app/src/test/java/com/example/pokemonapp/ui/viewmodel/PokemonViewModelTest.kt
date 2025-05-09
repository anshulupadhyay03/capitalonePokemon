package com.example.pokemonapp.ui.viewmodel

import com.example.pokemonapp.data.model.Pokemon
import com.example.pokemonapp.data.model.PokemonDetail
import com.example.pokemonapp.data.repository.PokemonRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonViewModelTest {
    private lateinit var viewModel: PokemonViewModel
    private val repository: PokemonRepository = mockk()
    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = PokemonViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadPokemonList emits Loading then Success when repository returns data`() = runTest(testScheduler) {
        // Arrange
        val newMockList = listOf(Pokemon("bulbasaur", "url"))
        coEvery { repository.getPokemonList(0) } returns  newMockList

        // Act
        val states = mutableListOf<Result<List<Pokemon>>>()
        val job = launch {
            viewModel.pokemonListState.toList(states)
        }
        viewModel.loadPokemonList(0)
        testScheduler.advanceUntilIdle()

        // Assert
        println("Collected states: $states")
        val lastTwo = states.takeLast(2)
        assertIs<Result.Loading>(lastTwo[0])
        assertIs<Result.Success<List<Pokemon>>>(lastTwo[1])
        assertEquals(newMockList, (lastTwo[1] as Result.Success).data)

        job.cancel()
    }

    @Test
    fun `loadPokemonList emits Loading then Error when repository throws exception`() = runTest(testScheduler) {
        // Arrange
        coEvery { repository.getPokemonList(0) } throws   Exception("Network error")

        // Act
        val states = mutableListOf<Result<List<Pokemon>>>()
        val job = launch {
            viewModel.pokemonListState.toList(states)
        }
        viewModel.loadPokemonList(0)
        testScheduler.advanceUntilIdle()

        // Assert
        println("Collected states: $states")
        assertIs<Result.Loading>(states[0])
        assertIs<Result.Error>(states[1])
        job.cancel()
    }

    @Test
    fun `loadPokemonDetail emits Loading then Success when repository returns data`() = runTest(testScheduler) {
        // Arrange
        val mockDetail = PokemonDetail("bulbasaur", 7, PokemonDetail.Sprites("img"))
        coEvery { repository.getPokemonDetail("bulbasaur") } returns mockDetail

        // Act
        val states = mutableListOf<Result<PokemonDetail>?>()
        val job = launch {
            viewModel.pokemonDetailState.toList(states)
        }
        viewModel.loadPokemonDetail("bulbasaur")
        testScheduler.advanceUntilIdle()

        // Assert
        println("Collected detail states: $states")
        assertEquals(null, states[0])
        assertIs<Result.Loading>(states[1])
        assertIs<Result.Success<PokemonDetail>>(states[2])
        assertEquals(mockDetail, (states[2] as Result.Success).data)

        job.cancel()
    }

    @Test
    fun `loadPokemonDetail emits Loading then Error when repository throws exception`() = runTest(testScheduler) {
        // Arrange
        coEvery { repository.getPokemonDetail("bulbasaur") } throws Exception("Network error")

        // Act
        val states = mutableListOf<Result<PokemonDetail>?>()
        val job = launch {
            viewModel.pokemonDetailState.toList(states)
        }
        viewModel.loadPokemonDetail("bulbasaur")
        testScheduler.advanceUntilIdle()

        // Assert
        println("Collected detail error states: $states")
        assertEquals(null, states[0])
        assertIs<Result.Loading>(states[1])
        assertIs<Result.Error>(states[2])
        assertEquals("Network error", (states[2] as Result.Error).message)

        job.cancel()
    }
}