package com.cwi.matheus.pokeapp.presentation.pokemon.viewHolder

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.cwi.matheus.pokeapp.databinding.ItemPokemonBinding

class PokemonAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvPokemonName: AppCompatTextView = ItemPokemonBinding.bind(itemView).tvPokemonName
    val ivPokemonImage: AppCompatImageView = ItemPokemonBinding.bind(itemView).ivPokemonImage
    val tvPokemonCount: AppCompatTextView = ItemPokemonBinding.bind(itemView).tvCount
    val root = ItemPokemonBinding.bind(itemView).root
}