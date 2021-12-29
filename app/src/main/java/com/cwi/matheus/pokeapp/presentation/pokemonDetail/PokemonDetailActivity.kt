package com.cwi.matheus.pokeapp.presentation.pokemonDetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.base.EXTRAS_POKEMON_ID
import com.cwi.matheus.pokeapp.base.EXTRAS_POKEMON_NAME
import com.cwi.matheus.pokeapp.databinding.ActivityPokemonDetailBinding
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

        intent.extras?.let {
            val pokemonId = it.getInt(EXTRAS_POKEMON_ID)
            val pokemonName = it.getString(EXTRAS_POKEMON_NAME)

            binding.tvPokemonId.text = pokemonId.toString()
            binding.tvPokemonName.text = pokemonName?.capitalize()
            viewModel.fetchPokemonDetail(pokemonId)
        }

        viewModel.loading.observe(this) {
            binding.viewLoading.root.visibleOrGone(it)
        }

        viewModel.data.observe(this) {
            binding.tvPokemonId.text = it.id.toString()
            binding.tvPokemonName.text = it.name.capitalize()
            binding.tvPokemonWeight.text = getString(R.string.txt_pokemon_weight, it.weight)
            binding.tvPokemonHeight.text = getString(R.string.txt_pokemon_height, it.height)
            Glide.with(this).load(it.imageUrl).into(binding.ivPokemonImage)

            // recycler view
            binding.rvPokemonStatList.adapter = PokemonDetailAdapter(this, it.stats)
            binding.rvPokemonStatList.layoutManager = GridLayoutManager(this, 2)
        }
    }
}