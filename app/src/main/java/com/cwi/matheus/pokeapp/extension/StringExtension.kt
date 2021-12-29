package com.cwi.matheus.pokeapp.extension

fun String.capitalize() : String {
    return this.split(" ").joinToString { it.substring(0, 1).uppercase() + it.substring(1) }
}