package com.cwi.matheus.pokeapp.presentation.pokemon.fragment.pokemonList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.base.EXTRAS_POKEMON_ID
import com.cwi.matheus.pokeapp.base.EXTRAS_POKEMON_NAME
import com.cwi.matheus.pokeapp.databinding.FragmentPokemonListBinding
import com.cwi.matheus.pokeapp.domain.entity.SimplePokemon
import com.cwi.matheus.pokeapp.extension.visibleOrGone
import com.cwi.matheus.pokeapp.presentation.pokemon.PokemonAdapter
import com.cwi.matheus.pokeapp.presentation.pokemon.viewModel.PokemonViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PokemonListFragment : Fragment() {

    private lateinit var binding: FragmentPokemonListBinding

    private val viewModel: PokemonViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchSimplePokemons(true)
        setupRecyclerView()

        viewModel.error.observe(viewLifecycleOwner) {
            binding.viewError.root.visibleOrGone(it)

            if (it) {
                binding.viewError.bTryAgain.setOnClickListener {
                    viewModel.fetchSimplePokemons(false)
                }
            }
        }
    }

    private fun setupRecyclerView() {

        context?.let { context ->
            binding.rvPokemonList
                .addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))

            val adapter = PokemonAdapter(context,
                onListItemClick = { simplePokemon -> navigateToPokemonDetail(simplePokemon) }
            )

            binding.rvPokemonList.adapter = adapter

            viewModel.data.observe(viewLifecycleOwner) { adapter.appendNewPokemons(it) }

            binding.rvPokemonList.layoutManager = LinearLayoutManager(context)

            setupScrollListener()
        }
    }

    private fun setupScrollListener() {
        val scrollListener: RecyclerView.OnScrollListener =
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                    val linearLayout = recyclerView.layoutManager as LinearLayoutManager
                    val lastItemListPosition = linearLayout.findLastCompletelyVisibleItemPosition()
                    val lastItemPosition = recyclerView.adapter?.itemCount?.minus(1)

                    if (lastItemListPosition == lastItemPosition) {
                        viewModel.fetchSimplePokemons(true)
                    }
                }
            }

        binding.rvPokemonList.removeOnScrollListener(scrollListener)
        binding.rvPokemonList.addOnScrollListener(scrollListener)
    }

    private fun navigateToPokemonDetail(simplePokemon: SimplePokemon) {
        findNavController().navigate(
            R.id.pokemon_detail_fragment,
            bundleOf(
                Pair(EXTRAS_POKEMON_ID, simplePokemon.pokemonId),
                Pair(EXTRAS_POKEMON_NAME, simplePokemon.name)
            )
        )
    }

}