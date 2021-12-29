package com.cwi.matheus.pokeapp.presentation.pokemonDetail.viewHolder

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cwi.matheus.pokeapp.databinding.ItemPokemonStatBinding

class PokemonDetailAdapterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val tvPokemonStatName : AppCompatTextView = ItemPokemonStatBinding.bind(itemView).tvPokemonStatName
    val tvPokemonStatValue : AppCompatTextView = ItemPokemonStatBinding.bind(itemView).tvPokemonStatValue
}