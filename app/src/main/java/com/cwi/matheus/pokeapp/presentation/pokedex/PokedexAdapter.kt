package com.cwi.matheus.pokeapp.presentation.pokedex

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.databinding.ItemPokedexBinding
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.extension.capitalize
import com.cwi.matheus.pokeapp.util.showConfirmDialog

class PokedexAdapter(
    private val context: Context,
    private val list : List<Pokemon>,
    private val onItemClick : (Pokemon) -> Unit,
    private val onConfirmSetFree : (Pokemon) -> Unit
) : RecyclerView.Adapter<PokedexViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder =
        PokedexViewHolder(LayoutInflater
            .from(context)
            .inflate(R.layout.item_pokedex, parent, false))


    override fun onBindViewHolder(holder: PokedexViewHolder, position: Int) {
        list[position].let { pokemon ->
            holder.tvPokemonName.text = pokemon.name.capitalize()
            Glide.with(context)
                .load(pokemon.imageUrl)
                .placeholder(AppCompatResources
                    .getDrawable(context, R.drawable.ic_baseline_pest_control_rodent_24))
                .into(holder.ivPokemonImage)

            holder.ivPokemonImage.setOnClickListener { onItemClick(pokemon) }
            holder.bSetFree.setOnClickListener { showSetFreeConfirmDialog(pokemon) }
        }
    }

    private fun showSetFreeConfirmDialog(pokemon: Pokemon) {
        val titleText = context.getString(R.string.txt_set_free_pokemon_dialog_title_text, pokemon.name)

        showConfirmDialog(context, titleText,
            onConfirm = {
            onConfirmSetFree(pokemon)
        })
    }

    override fun getItemCount(): Int = list.size
}

class PokedexViewHolder(viewHolder : View) : RecyclerView.ViewHolder(viewHolder) {
    val tvPokemonName = ItemPokedexBinding.bind(viewHolder).tvPokemonName
    val ivPokemonImage = ItemPokedexBinding.bind(viewHolder).ivPokemonImage
    val bSetFree = ItemPokedexBinding.bind(viewHolder).bSetFree
}
