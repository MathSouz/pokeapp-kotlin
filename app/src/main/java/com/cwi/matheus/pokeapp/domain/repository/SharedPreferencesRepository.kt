package com.cwi.matheus.pokeapp.domain.repository

interface SharedPreferencesRepository {
    fun setBiometryNeedPreference(needed : Boolean)
    fun isBiometryNeed() : Boolean
    fun setDarkMode(darkMode : Boolean)
    fun isDarkMode() : Boolean

}