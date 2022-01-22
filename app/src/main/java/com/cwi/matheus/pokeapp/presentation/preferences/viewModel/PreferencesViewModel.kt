package com.cwi.matheus.pokeapp.presentation.preferences.viewModel

import com.cwi.matheus.pokeapp.domain.repository.SharedPreferencesRepository
import com.cwi.matheus.pokeapp.presentation.base.BaseViewModel

class PreferencesViewModel(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) : BaseViewModel() {
    fun setBiometryNeedPreference(needed: Boolean) =
        sharedPreferencesRepository.setBiometryNeedPreference(needed)

    fun isBiometryNeed(): Boolean =
        sharedPreferencesRepository.isBiometryNeed()

    fun setDarkMode(darkMode: Boolean) =
        sharedPreferencesRepository.setDarkMode(darkMode)

    fun isDarkMode(): Boolean =
        sharedPreferencesRepository.isDarkMode()
}