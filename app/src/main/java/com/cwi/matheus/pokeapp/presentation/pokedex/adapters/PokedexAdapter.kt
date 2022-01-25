package com.cwi.matheus.pokeapp.presentation.pokedex.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.extension.visibleOrGone
import com.cwi.matheus.pokeapp.presentation.pokedex.viewHolder.PokedexViewHolder
import java.util.*

class PokedexAdapter(
    private val context: Context,
    private val list: List<Pokemon>,
    private val onItemClick: (Pokemon) -> Unit
) : RecyclerView.Adapter<PokedexViewHolder>() {

    private val _selectedPokemons = mutableListOf<Pokemon>()
    val selectedPokemons: List<Pokemon> = _selectedPokemons

    private var selectionListener: OnPokemonSelectionListener? = null

    fun clearSelections() {
        _selectedPokemons.clear()
        notifyChanges()
    }

    fun setOnSelectionListener(onPokemonSelectionListener: OnPokemonSelectionListener) {
        selectionListener = onPokemonSelectionListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder =
        PokedexViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.item_pokedex, parent, false)
        )


    override fun onBindViewHolder(holder: PokedexViewHolder, position: Int) {
        list[position].let { pokemon ->
            holder.etPokemonName.text = pokemon.name

            Glide.with(context)
                .load(pokemon.imageUrl)
                .placeholder(
                    AppCompatResources
                        .getDrawable(context, R.drawable.ic_baseline_pest_control_rodent_24)
                )
                .into(holder.ivPokemonImage)

            val date = Calendar.getInstance()
            date.timeInMillis = pokemon.createdAt
            val day = date.get(Calendar.DAY_OF_MONTH)
            val month = date.get(Calendar.MONTH) + 1
            val year = date.get(Calendar.YEAR)
            val hour = date.get(Calendar.HOUR_OF_DAY)
            val minute = date.get(Calendar.MINUTE)
            val second = date.get(Calendar.SECOND)

            val timeDistance = Calendar.getInstance()
            timeDistance.timeInMillis = timeDistance.timeInMillis - pokemon.createdAt
            val mins = timeDistance.timeInMillis / 1000 / 60

            val dateString =
                context.getString(
                    R.string.txt_pokemon_date,
                    day,
                    month,
                    year,
                    hour,
                    minute,
                    second,
                    mins
                )
            holder.tvDate.text = dateString

            holder.rvTypes.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            holder.rvTypes.adapter = PokemonTypeAdapter(context, pokemon.types)

            holder.root.setOnClickListener {
                if (_selectedPokemons.isEmpty()) {
                    onItemClick(pokemon)
                } else {
                    selectPokemon(pokemon)
                }
            }

            holder.root.setOnLongClickListener {
                selectPokemon(pokemon)
                true
            }

            holder.ivSelected.visibleOrGone(isPokemonSelected(pokemon))

        }
    }

    private fun isPokemonSelected(pokemon: Pokemon): Boolean {
        return _selectedPokemons.contains(pokemon)
    }

    private fun selectPokemon(pokemon: Pokemon) {
        if (isPokemonSelected(pokemon)) {
            _selectedPokemons.remove(pokemon)
        } else {
            _selectedPokemons.add(pokemon)
        }
        notifyChanges()
    }

    private fun notifyChanges() {
        notifyDataSetChanged()
        selectionListener?.onChanged()
    }

    override fun getItemCount(): Int = list.size
}
