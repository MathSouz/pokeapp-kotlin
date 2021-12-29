package com.cwi.matheus.pokeapp.presentation.pokemon.viewHolder

import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.cwi.matheus.pokeapp.databinding.ItemPokemonBinding

class PokemonAdapterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val tvPokemonName : AppCompatTextView = ItemPokemonBinding.bind(itemView).tvPokemonName
    val ivPokemonImage : AppCompatImageView = ItemPokemonBinding.bind(itemView).ivPokemonImage
    val ibFavorite : AppCompatImageButton = ItemPokemonBinding.bind(itemView).ibFavorite
    val root = ItemPokemonBinding.bind(itemView).root
}