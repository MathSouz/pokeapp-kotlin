package com.cwi.matheus.pokeapp.presentation.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.presentation.pokedex.PokedexActivity
import com.cwi.matheus.pokeapp.presentation.pokemon.PokemonHostActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BaseBottomNavigationActivity : AppCompatActivity() {

    abstract val tab: Int
    abstract fun getBottomNavigation(): BottomNavigationView

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    override fun onResume() {
        super.onResume()
        setUpBottomNavigationActions()
        selectCurrentTab()
    }

    private fun setUpBottomNavigationActions() {

        getBottomNavigation().setOnItemSelectedListener {
            if (it.itemId != this.tab) when (it.itemId) {
                R.id.menu_wild_pokemons -> {
                    val intent = Intent(this, PokemonHostActivity::class.java)
                    startActivity(intent)
                }
                R.id.menu_pokedex_pokemons -> {
                    val intent = Intent(this, PokedexActivity::class.java)
                    startActivity(intent)
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun selectCurrentTab() {
        getBottomNavigation().selectedItemId = tab
    }


}