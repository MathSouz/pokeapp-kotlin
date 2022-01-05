package com.cwi.matheus.pokeapp.presentation.pokemon

import android.os.Bundle
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.databinding.ActivityPokemonHostBinding
import com.cwi.matheus.pokeapp.extension.visibleOrGone
import com.cwi.matheus.pokeapp.presentation.base.BaseBottomNavigationActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonHostActivity : BaseBottomNavigationActivity() {

    private lateinit var binding : ActivityPokemonHostBinding

    private val viewModel : PokemonViewModel by viewModel()

    override val tab: Int = R.id.menu_wild_pokemons
    override fun getBottomNavigation(): BottomNavigationView =
        binding.viewBottomNavigation.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.loading.observe(this) {
            binding.viewLoading.root.visibleOrGone(it)
        }
    }
}