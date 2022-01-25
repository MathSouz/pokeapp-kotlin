package com.cwi.matheus.pokeapp.presentation.pokedex.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cwi.matheus.pokeapp.databinding.ViewTypeBinding

class PokemonTypeViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
    val root = ViewTypeBinding.bind(viewHolder).root
    val tvTypeName = ViewTypeBinding.bind(viewHolder).tvTypeName
}