package com.cwi.matheus.pokeapp.presentation.pokemon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.StringUtil
import com.bumptech.glide.Glide
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.databinding.ItemPokemonBinding
import com.cwi.matheus.pokeapp.domain.entity.SimplePokemon
import com.cwi.matheus.pokeapp.extension.capitalize
import com.cwi.matheus.pokeapp.presentation.pokemon.viewHolder.PokemonAdapterViewHolder

class PokemonAdapter(
    private val context : Context,
    private val list : List<SimplePokemon>,
    private val onListItemClick : (SimplePokemon) -> Unit) :
    RecyclerView.Adapter<PokemonAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapterViewHolder =
        PokemonAdapterViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false ))

    override fun onBindViewHolder(holder: PokemonAdapterViewHolder, position: Int) {
        list[position].let { simplePokemon ->
            holder.tvPokemonName.text = simplePokemon.name.capitalize()
            holder.root.setOnClickListener {
                onListItemClick(simplePokemon)
            }

            val capturedPokemonIcon = AppCompatResources.getDrawable(context, R.drawable.pokemon_enable)
            val notCapturedPokemonIcon = AppCompatResources.getDrawable(context, R.drawable.pokemon_disable)

            holder.ibFavorite.setImageDrawable(
                if(simplePokemon.captured)
                    capturedPokemonIcon
                else
                    notCapturedPokemonIcon
            )

            Glide.with(context)
                .load(simplePokemon.imageUrl)
                .placeholder(R.drawable.ic_baseline_pest_control_rodent_24)
                .into(holder.ivPokemonImage)
        }
    }

    override fun getItemCount(): Int = list.size
}