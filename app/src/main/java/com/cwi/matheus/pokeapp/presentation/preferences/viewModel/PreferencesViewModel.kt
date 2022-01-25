package com.cwi.matheus.pokeapp.presentation.preferences.viewModel

import com.cwi.matheus.pokeapp.base.SHARED_PREFERENCE_BIOMETRIC_NEEDED
import com.cwi.matheus.pokeapp.base.SHARED_PREFERENCE_DARK_MODE
import com.cwi.matheus.pokeapp.domain.repository.SharedPreferencesRepository
import com.cwi.matheus.pokeapp.presentation.base.BaseViewModel

class PreferencesViewModel(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) : BaseViewModel() {
    fun setBiometryNeedPreference(needed: Boolean) =
        sharedPreferencesRepository.writeBoolean(SHARED_PREFERENCE_BIOMETRIC_NEEDED, needed)

    fun isBiometryNeed(): Boolean =
        sharedPreferencesRepository.readBoolean(SHARED_PREFERENCE_BIOMETRIC_NEEDED, false)

    fun setDarkMode(darkMode: Boolean) =
        sharedPreferencesRepository.writeBoolean(SHARED_PREFERENCE_DARK_MODE, darkMode)

    fun isDarkMode(): Boolean =
        sharedPreferencesRepository.readBoolean(SHARED_PREFERENCE_DARK_MODE, false)
}