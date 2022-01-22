package com.cwi.matheus.pokeapp.data.sharedPreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.cwi.matheus.pokeapp.base.AUTH_SHARED_PREFERENCES
import com.cwi.matheus.pokeapp.base.DARK_MODE_SHARED_PREFERENCES
import com.cwi.matheus.pokeapp.base.SHARED_PREFERENCE_BIOMETRIC_NEEDED
import com.cwi.matheus.pokeapp.base.SHARED_PREFERENCE_DARK_MODE
import com.cwi.matheus.pokeapp.domain.repository.SharedPreferencesRepository

class SharedPreferencesRepositoryImpl(private val context: Context) : SharedPreferencesRepository {

    override fun setBiometryNeedPreference(needed: Boolean) {
        context.getSharedPreferences(AUTH_SHARED_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .edit()
            .putBoolean(SHARED_PREFERENCE_BIOMETRIC_NEEDED, needed)
            .apply()
    }

    override fun isBiometryNeed() =
        context.getSharedPreferences(AUTH_SHARED_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .getBoolean(SHARED_PREFERENCE_BIOMETRIC_NEEDED, false)

    override fun setDarkMode(darkMode: Boolean) {
        context.getSharedPreferences(DARK_MODE_SHARED_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .edit()
            .putBoolean(SHARED_PREFERENCE_DARK_MODE, darkMode)
            .apply()
    }

    override fun isDarkMode() =
        context.getSharedPreferences(DARK_MODE_SHARED_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .getBoolean(SHARED_PREFERENCE_DARK_MODE, false)
}