package com.cwi.matheus.pokeapp.presentation.pokedex

import android.content.Intent
import android.os.Bundle
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.base.EXTRAS_POKEMON_ID
import com.cwi.matheus.pokeapp.base.EXTRAS_POKEMON_NAME
import com.cwi.matheus.pokeapp.databinding.ActivityPokedexBinding
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.extension.visibleOrGone
import com.cwi.matheus.pokeapp.presentation.base.BaseBottomNavigationActivity
import com.cwi.matheus.pokeapp.presentation.extension.showConfirmDialog
import com.cwi.matheus.pokeapp.presentation.pokedex.adapters.OnPokemonSelectionListener
import com.cwi.matheus.pokeapp.presentation.pokedex.adapters.PokedexAdapter
import com.cwi.matheus.pokeapp.presentation.pokedex.viewModel.PokedexViewModel
import com.cwi.matheus.pokeapp.presentation.pokemonDetail.PokemonDetailActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexActivity : BaseBottomNavigationActivity() {

    private lateinit var binding: ActivityPokedexBinding
    private val viewModel: PokedexViewModel by viewModel()

    override val tab: Int = R.id.menu_pokedex_pokemons

    override fun getBottomNavigation(): BottomNavigationView = binding.bnvNav.root

    private lateinit var adapter: PokedexAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokedexBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPokedexList.layoutManager = LinearLayoutManager(this)

        setupAdapter()

        viewModel.loading.observe(this) {
            binding.viewLoading.root.visibleOrGone(it)
        }
    }

    private fun startDetailActivity(pokemon: Pokemon) {
        val intent = Intent(
            this@PokedexActivity,
            PokemonDetailActivity::class.java
        )
        intent.putExtra(EXTRAS_POKEMON_ID, pokemon.id)
        intent.putExtra(EXTRAS_POKEMON_NAME, pokemon.name)
        startActivity(intent)
    }

    private fun setupAdapter() {
        viewModel.pokedex.observe(this) { list ->
            this.adapter = PokedexAdapter(this, list,
                onItemClick = { pokemon ->
                    startDetailActivity(pokemon)
                })

            this.adapter.setOnSelectionListener(object : OnPokemonSelectionListener {
                override fun onChanged() {
                    onChangeSelection()
                }
            })

            binding.toolbar.menu.getItem(0).setOnMenuItemClickListener {
                onMenuDeleteClick()
                true
            }

            binding.rvPokedexList.adapter = this.adapter
            binding.viewPokedexEmptyList.root.visibleOrGone(list.isEmpty())
        }
    }

    private fun onMenuDeleteClick() {
        val undoneableAction = getString(R.string.txt_undoneable_action)
        showConfirmDialog(undoneableAction) {
            for (selectedPokemon in adapter.selectedPokemons) {
                viewModel.deletePokemonFromPokedex(selectedPokemon)
            }

            this.adapter.clearSelections()
        }
    }

    private fun onChangeSelection() {
        val selectedPokemons = adapter.selectedPokemons
        binding.toolbar.visibleOrGone(selectedPokemons.isNotEmpty())
        val selectedPokemonsText = getString(R.string.txt_selected, selectedPokemons.size)
        binding.toolbar.title = selectedPokemonsText
        binding.toolbar.menu.children.iterator().forEach {
            it.isVisible = selectedPokemons.isNotEmpty()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchPokedex()
        binding.toolbar.visibleOrGone(false)
    }
}