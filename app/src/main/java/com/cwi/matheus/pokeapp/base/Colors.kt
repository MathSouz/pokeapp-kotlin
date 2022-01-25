package com.cwi.matheus.pokeapp.base

import com.cwi.matheus.pokeapp.R

class Colors {
    companion object {
        private val colorTypeMap = mapOf(
            Pair("fire", R.color.fire),
            Pair("grass", R.color.grass),
            Pair("normal", R.color.normal),
            Pair("flying", R.color.flying),
            Pair("poison", R.color.poison),
            Pair("water", R.color.water),
            Pair("bug", R.color.bug),
        )

        fun getColor(typeName: String): Int? {
            return colorTypeMap[typeName]
        }
    }
}