package com.cwi.matheus.pokeapp.presentation.pokedex

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.base.EXTRAS_POKEMON_ID
import com.cwi.matheus.pokeapp.base.EXTRAS_POKEMON_NAME
import com.cwi.matheus.pokeapp.databinding.ActivityPokedexBinding
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.extension.capitalize
import com.cwi.matheus.pokeapp.extension.visibleOrGone
import com.cwi.matheus.pokeapp.presentation.base.BaseBottomNavigationActivity
import com.cwi.matheus.pokeapp.presentation.pokedex.viewModel.PokedexViewModel
import com.cwi.matheus.pokeapp.presentation.pokemonDetail.PokemonDetailActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexActivity : BaseBottomNavigationActivity() {

    private lateinit var binding: ActivityPokedexBinding
    private val viewModel: PokedexViewModel by viewModel()

    override val tab: Int = R.id.menu_pokedex_pokemons

    override fun getBottomNavigation(): BottomNavigationView = binding.bnvNav.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokedexBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPokedexList.layoutManager = GridLayoutManager(this, 2)

        viewModel.pokedex.observe(this) { list ->
            binding.rvPokedexList.adapter = PokedexAdapter(this, list,
                onItemClick = { pokemon ->
                    val intent = Intent(
                        this@PokedexActivity,
                        PokemonDetailActivity::class.java
                    )

                    intent.putExtra(EXTRAS_POKEMON_ID, pokemon.id)
                    intent.putExtra(EXTRAS_POKEMON_NAME, pokemon.name)
                    startActivity(intent)
                },
                onConfirmSetFree = { deletedPokemon ->
                    viewModel.deletePokemonFromPokedex(deletedPokemon)
                    showFreePokemonMessage(deletedPokemon)
                })

            binding.viewPokedexEmptyList.root.visibleOrGone(list.isEmpty())
        }

        viewModel.loading.observe(this) {
            binding.viewLoading.root.visibleOrGone(it)
        }
    }

    private fun showFreePokemonMessage(deletedPokemon: Pokemon) {
        val alertMessage = getString(R.string.txt_pokemon_free, deletedPokemon.name.capitalize())

        Snackbar.make(binding.rvPokedexList, alertMessage, Snackbar.LENGTH_LONG)
            .setAction(R.string.txt_undo) {
                viewModel.addPokemonToPokedex(deletedPokemon)
            }
            .show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchPokedex()
    }
}