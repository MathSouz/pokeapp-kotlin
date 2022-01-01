package com.cwi.matheus.pokeapp.presentation.pokemonDetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.base.EXTRAS_POKEMON_ID
import com.cwi.matheus.pokeapp.base.EXTRAS_POKEMON_NAME
import com.cwi.matheus.pokeapp.databinding.ActivityPokemonDetailBinding
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.extension.capitalize
import com.cwi.matheus.pokeapp.extension.visibleOrGone
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonDetailActivity : AppCompatActivity() {

    private val viewModel : PokemonDetailViewModel by viewModel()
    private lateinit var binding : ActivityPokemonDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.extras?.let {
            val pokemonId = it.getInt(EXTRAS_POKEMON_ID)
            val pokemonName = it.getString(EXTRAS_POKEMON_NAME)
            val capitalizedPokemonName = pokemonName?.capitalize()

            binding.tvPokemonId.text = pokemonId.toString()
            binding.tvPokemonName.text = capitalizedPokemonName
            viewModel.fetchPokemonDetail(pokemonId)
            supportActionBar?.title = capitalizedPokemonName
        }

        viewModel.loading.observe(this) {
            binding.viewLoading.root.visibleOrGone(it)
        }

        viewModel.currentPokemon.observe(this) { pokemon ->
            binding.tvPokemonId.text = pokemon.id.toString()
            binding.tvPokemonName.text = pokemon.name.capitalize()
            binding.tvPokemonWeight.text = getString(R.string.txt_pokemon_weight, pokemon.weight)
            binding.tvPokemonHeight.text = getString(R.string.txt_pokemon_height, pokemon.height)
            Glide.with(this).load(pokemon.imageUrl).into(binding.ivPokemonImage)
            binding.efabCapture.visibleOrGone(true)
            binding.efabCapture.setOnClickListener {
                pokemon.captured = !pokemon.captured
                viewModel.setCaptured(pokemon)
                viewModel.fetchPokemonDetail(pokemon.id)
            }

            updateExtendedFloatingActionButtonTextAccordingToPokemonState(pokemon)

            binding.rvPokemonStatList.adapter = PokemonDetailAdapter(this, pokemon.stats)
            binding.rvPokemonStatList.layoutManager = GridLayoutManager(this, 2)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun updateExtendedFloatingActionButtonTextAccordingToPokemonState(pokemon: Pokemon) {
        binding.efabCapture.text =
            if(pokemon.captured) getString(R.string.txt_set_free)
            else getString(R.string.txt_capture)
    }

}