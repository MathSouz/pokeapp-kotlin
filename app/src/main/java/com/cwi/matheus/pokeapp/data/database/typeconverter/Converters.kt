package com.cwi.matheus.pokeapp.data.database.typeconverter

import androidx.room.TypeConverter
import com.cwi.matheus.pokeapp.data.network.mapper.PokemonStatMapper
import com.cwi.matheus.pokeapp.domain.entity.PokemonStat
import com.cwi.matheus.pokeapp.extension.parseToString

class Converters {
    @TypeConverter
    fun fromPokemonStatList(value: List<PokemonStat>): String {
        return value.parseToString()
    }

    @TypeConverter
    fun stringToPokemonStatList(date: String): List<PokemonStat> {
        return PokemonStatMapper().fromStringToDomainList(date)
    }
}