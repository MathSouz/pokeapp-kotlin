package com.cwi.matheus.pokeapp.data.database.typeconverter

import androidx.room.TypeConverter
import com.cwi.matheus.pokeapp.data.database.entity.PokemonStatList
import com.cwi.matheus.pokeapp.data.database.entity.PokemonTypeList
import com.cwi.matheus.pokeapp.domain.entity.PokemonStat
import com.google.gson.Gson

const val STRINGIFIED_POKEMON_STAT_LIST_SEPARATOR = "&"

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromPokemonStatList(value: PokemonStatList): String {
        return gson.toJson(value)
    }

    private fun pokemonStatToString(pokemonStat: PokemonStat) =
        "${pokemonStat.stat.name}:baseStat=${pokemonStat.baseStat},effort=${pokemonStat.effort}"

    @TypeConverter
    fun stringToPokemonStatList(date: String): PokemonStatList {
        return gson.fromJson(date, PokemonStatList::class.java)
    }

    @TypeConverter
    fun fromPokemonTypeListToJSON(value: PokemonTypeList): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToPokemonTypeList(value: String): PokemonTypeList {
        return gson.fromJson(value, PokemonTypeList::class.java)
    }
}