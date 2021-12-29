package com.cwi.matheus.pokeapp.presentation.pokedex

import android.os.Bundle
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.databinding.ActivityPokedexBinding
import com.cwi.matheus.pokeapp.presentation.base.BaseBottomNavigationActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class PokedexActivity : BaseBottomNavigationActivity() {

    private lateinit var binding : ActivityPokedexBinding

    override val tab: Int = R.id.menu_pokedex_pokemons

    override fun getBottomNavigation(): BottomNavigationView = binding.bnvNav.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokedexBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}