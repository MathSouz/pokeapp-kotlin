package com.cwi.matheus.pokeapp.presentation.pokemon.fragment.pokemonDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.base.EXTRAS_POKEMON_ID
import com.cwi.matheus.pokeapp.base.EXTRAS_POKEMON_NAME
import com.cwi.matheus.pokeapp.databinding.FragmentPokemonDetailBinding
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.extension.capitalize
import com.cwi.matheus.pokeapp.extension.visibleOrGone
import com.cwi.matheus.pokeapp.presentation.pokemonDetail.PokemonDetailAdapter
import com.cwi.matheus.pokeapp.presentation.pokemonDetail.PokemonDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonDetailFragment : Fragment() {

    private val viewModel : PokemonDetailViewModel by viewModel()
    private lateinit var binding : FragmentPokemonDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let { context ->

            arguments?.let { bundle ->
                val pokemonId = bundle.getInt(EXTRAS_POKEMON_ID)
                val pokemonName = bundle.getString(EXTRAS_POKEMON_NAME)
                val capitalizedPokemonName = pokemonName?.capitalize()

                binding.tvPokemonId.text = pokemonId.toString()
                binding.tvPokemonName.text = capitalizedPokemonName
                viewModel.fetchPokemonDetail(pokemonId)

                viewModel.error.observe(viewLifecycleOwner) {
                    binding.viewError.root.visibleOrGone(it)

                    if(it) {
                        binding.viewError.bTryAgain.setOnClickListener {
                            viewModel.fetchPokemonDetail(pokemonId)
                        }
                    }
                }
            }

            viewModel.loading.observe(viewLifecycleOwner) {
                binding.viewLoading.root.visibleOrGone(it)
            }

            viewModel.currentPokemon.observe(viewLifecycleOwner) { pokemon ->
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

                binding.rvPokemonStatList.adapter = PokemonDetailAdapter(context, pokemon.stats)
                binding.rvPokemonStatList.layoutManager = GridLayoutManager(context, 2)
            }
        }
    }

    private fun updateExtendedFloatingActionButtonTextAccordingToPokemonState(pokemon: Pokemon) {
        binding.efabCapture.text =
            if(pokemon.captured) getString(R.string.txt_set_free)
            else getString(R.string.txt_capture)
    }
}