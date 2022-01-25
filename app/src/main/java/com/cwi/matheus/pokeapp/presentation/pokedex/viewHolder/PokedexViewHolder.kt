package com.cwi.matheus.pokeapp.presentation.pokedex.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cwi.matheus.pokeapp.databinding.ItemPokedexBinding

class PokedexViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
    val tvPokemonName = ItemPokedexBinding.bind(viewHolder).tvPokemonName
    val ivPokemonImage = ItemPokedexBinding.bind(viewHolder).ivPokemonImage
    val ivSelected = ItemPokedexBinding.bind(viewHolder).ivSelected
    val tvDate = ItemPokedexBinding.bind(viewHolder).tvDate
    val rvTypes = ItemPokedexBinding.bind(viewHolder).rvTypes
    val root = ItemPokedexBinding.bind(viewHolder).root
}