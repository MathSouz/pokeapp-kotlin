package com.cwi.matheus.pokeapp.presentation.pokemonDetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.domain.entity.PokemonStat
import com.cwi.matheus.pokeapp.presentation.pokemonDetail.viewHolder.PokemonDetailAdapterViewHolder

class PokemonDetailAdapter(
    private val context: Context,
    private val list: List<PokemonStat>
) :
    RecyclerView.Adapter<PokemonDetailAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonDetailAdapterViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.item_pokemon_stat, parent, false)
        return PokemonDetailAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonDetailAdapterViewHolder, position: Int) {
        list[position].let {
            val baseStat: Int = it.baseStat
            val statName: String = it.stat.name
            holder.tvPokemonStatName.text = statName
            holder.tvPokemonStatValue.text = baseStat.toString()
        }
    }

    override fun getItemCount(): Int = list.size
}