package com.cwi.matheus.pokeapp.presentation.pokemon

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.domain.entity.SimplePokemon
import com.cwi.matheus.pokeapp.extension.capitalize
import com.cwi.matheus.pokeapp.presentation.pokemon.viewHolder.PokemonAdapterViewHolder

class PokemonAdapter(
    private val context : Context,
    var list : List<SimplePokemon> = listOf(),
    private val onListItemClick : (SimplePokemon) -> Unit,
    private val onCaptureClick:(SimplePokemon) -> Unit) :
    RecyclerView.Adapter<PokemonAdapterViewHolder>() {

    fun appendNewPokemons(newPokemonList : List<SimplePokemon>) {
        list = newPokemonList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapterViewHolder =
        PokemonAdapterViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false ))

    override fun onBindViewHolder(holder: PokemonAdapterViewHolder, position: Int) {
        list[position].let { simplePokemon ->
            holder.tvPokemonName.text = simplePokemon.name.capitalize()
            holder.root.setOnClickListener {
                onListItemClick(simplePokemon)
            }

            with(holder.ibFavorite) {
                setImageDrawable(getFavoriteIcon(simplePokemon))
                setOnClickListener {
                    simplePokemon.captured = !simplePokemon.captured
                    setImageDrawable(getFavoriteIcon(simplePokemon))
                    onCaptureClick(simplePokemon)
                }
            }

            Glide.with(context)
                .load(simplePokemon.imageUrl)
                .placeholder(R.drawable.ic_baseline_pest_control_rodent_24)
                .into(holder.ivPokemonImage)
        }
    }

    private fun getFavoriteIcon(pokemon: SimplePokemon) = ContextCompat.getDrawable(
        context,
        if (pokemon.captured) R.drawable.pokemon_enable
        else R.drawable.pokemon_disable
    )


    override fun getItemCount(): Int = list.size
}