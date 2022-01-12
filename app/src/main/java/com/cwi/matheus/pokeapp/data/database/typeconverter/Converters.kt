package com.cwi.matheus.pokeapp.data.database.typeconverter

import androidx.room.TypeConverter
import com.cwi.matheus.pokeapp.data.network.mapper.PokemonStatMapper
import com.cwi.matheus.pokeapp.domain.entity.PokemonStat

const val STRINGIFIED_POKEMON_STAT_LIST_SEPARATOR = "&"

class Converters {

    @TypeConverter
    fun fromPokemonStatList(value: List<PokemonStat>): String {
        return value.joinToString(
            separator = STRINGIFIED_POKEMON_STAT_LIST_SEPARATOR,
            transform = {
                pokemonStatToString(it)
            })
    }

    private fun pokemonStatToString(pokemonStat: PokemonStat) =
        "${pokemonStat.stat.name}:baseStat=${pokemonStat.baseStat},effort=${pokemonStat.effort}"

    @TypeConverter
    fun stringToPokemonStatList(date: String): List<PokemonStat> {
        return PokemonStatMapper().fromStringToDomainList(date)
    }
}