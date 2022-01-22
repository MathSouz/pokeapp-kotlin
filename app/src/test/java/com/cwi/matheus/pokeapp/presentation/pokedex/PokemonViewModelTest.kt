package com.cwi.matheus.pokeapp.presentation.pokedex

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.domain.entity.PokemonStat
import com.cwi.matheus.pokeapp.domain.entity.SimplePokemon
import com.cwi.matheus.pokeapp.domain.entity.Stat
import com.cwi.matheus.pokeapp.domain.repository.PokeApiLocalRepository
import com.cwi.matheus.pokeapp.domain.repository.PokeApiRepository
import com.cwi.matheus.pokeapp.extension.test
import com.cwi.matheus.pokeapp.presentation.pokemon.PokemonViewModel
import com.cwi.matheus.pokeapp.presentation.pokemon.viewModel.PokemonViewModel
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class PokemonViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var viewModel: PokemonViewModel

    private val pokeApiRepository: PokeApiRepository = mockk()
    private val pokeApiLocalRepository : PokeApiLocalRepository = mockk()


    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = PokemonViewModel(pokeApiRepository, pokeApiLocalRepository)
    }

    @Test
    fun whenFetchWithNextPageFlag_thenFetchPokemonsPaging() {

        val viewObserver = viewModel.data.test()

        val simplePokemonList = listOf(
            SimplePokemon(0, "Pikachu", "pikachu.png", true)
        )

        every { pokeApiLocalRepository.getAll() } returns emptyList()
        coEvery { pokeApiRepository.getPokemonList(0) } returns simplePokemonList

        viewModel.fetchSimplePokemons(true)

        verify { viewObserver.onChanged(any()) }
        coVerify { pokeApiRepository.getPokemonList(0) }
    }

    @Test
    fun givenPokemon_whenIsCaptured_thenSaveToLocalRepository() {
        val statList = listOf(PokemonStat(10, 10, Stat("HP")))
        val simplePokemon = SimplePokemon(0, "Pikachu", "pikachu.png", true)
        val pokemon = Pokemon(0, "Pikachu", 10, 10, "pikachu.png", statList, true)

        coEvery { pokeApiRepository.getPokemonByID(simplePokemon.pokemonId) }.returns(pokemon)
        every { pokeApiLocalRepository.add(pokemon) }.returns(Unit)

        viewModel.updateLocalRepositoryFromPokemonCaptureState(simplePokemon)

        verify { pokeApiLocalRepository.add(pokemon) }
    }

    @Test
    fun givenPokemon_whenIsNotCaptured_thenRemoveFromLocalRepository() {
        val statList = listOf(PokemonStat(10, 10, Stat("HP")))
        val simplePokemon = SimplePokemon(0, "Pikachu", "pikachu.png", false)
        val pokemon = Pokemon(0, "Pikachu", 10, 10, "pikachu.png", statList, false)

        every { pokeApiLocalRepository.remove(pokemon.id) } returns Unit

        viewModel.updateLocalRepositoryFromPokemonCaptureState(simplePokemon)

        verify { pokeApiLocalRepository.remove(pokemon.id) }
    }
}