package com.cwi.matheus.pokeapp.presentation.pokedex.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.domain.entity.PokemonType
import com.cwi.matheus.pokeapp.extension.capitalize
import com.cwi.matheus.pokeapp.extension.getMappedColor
import com.cwi.matheus.pokeapp.presentation.pokedex.viewHolder.PokemonTypeViewHolder

class PokemonTypeAdapter(
    private val context: Context,
    private val list: List<PokemonType>
) : RecyclerView.Adapter<PokemonTypeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonTypeViewHolder =
        PokemonTypeViewHolder(
            LayoutInflater.from(context).inflate(R.layout.view_type, parent, false)
        )

    override fun onBindViewHolder(holder: PokemonTypeViewHolder, position: Int) {
        list[position].let { pokemonType ->
            context.run {
                val color = pokemonType.getMappedColor()
                var resourceColor = this.getColor(R.color.transparent_black)

                color?.let {
                    resourceColor = this.getColor(it)
                }

                holder.root.setCardBackgroundColor(resourceColor)
            }

            holder.tvTypeName.text = pokemonType.name.capitalize()
        }
    }

    override fun getItemCount(): Int = list.size
}