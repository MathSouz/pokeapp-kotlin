package com.cwi.matheus.pokeapp.presentation.pokemon

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.base.EXTRAS_POKEMON_ID
import com.cwi.matheus.pokeapp.base.EXTRAS_POKEMON_NAME
import com.cwi.matheus.pokeapp.databinding.ActivityPokemonBinding
import com.cwi.matheus.pokeapp.domain.entity.SimplePokemon
import com.cwi.matheus.pokeapp.extension.visibleOrGone
import com.cwi.matheus.pokeapp.presentation.base.BaseBottomNavigationActivity
import com.cwi.matheus.pokeapp.presentation.pokemonDetail.PokemonDetailActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonActivity : BaseBottomNavigationActivity() {

    private val viewModel : PokemonViewModel by viewModel()

    private lateinit var binding : ActivityPokemonBinding
    override val tab: Int = R.id.menu_wild_pokemons

    override fun getBottomNavigation(): BottomNavigationView = binding.bnvNav.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fetchSimplePokemon()

        viewModel.loading.observe(this@PokemonActivity) {
            binding.viewLoading.root.visibleOrGone(it)
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvPokemonList.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))

        viewModel.data.observe(this@PokemonActivity) {
            binding.rvPokemonList.adapter = PokemonAdapter(this@PokemonActivity, it,
                onListItemClick = { simplePokemon ->  startPokemonDetailActivity(simplePokemon) }
            )
            binding.rvPokemonList.layoutManager = LinearLayoutManager(this)

            binding.rvPokemonList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val linearLayout = recyclerView.layoutManager as LinearLayoutManager

                    if(linearLayout.findLastCompletelyVisibleItemPosition() == it.size - 1) {
                        viewModel.fetchSimplePokemon()
                    }
                }
            })
        }
    }

    private fun startPokemonDetailActivity(simplePokemon : SimplePokemon) {
        val intent = Intent(this@PokemonActivity, PokemonDetailActivity::class.java)
        intent.putExtra(EXTRAS_POKEMON_ID, simplePokemon.id)
        intent.putExtra(EXTRAS_POKEMON_NAME, simplePokemon.name)
        startActivity(intent)
    }
}