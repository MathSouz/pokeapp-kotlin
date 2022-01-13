package com.cwi.matheus.pokeapp.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class SharedPreferencesManager(
    private val context: Context) {

    fun setBiometryNeedPreference(needed : Boolean) {
        context.getSharedPreferences(AUTH_SHARED_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .edit()
            .putBoolean(SHARED_PREFERENCE_BIOMETRIC_NEEDED, needed)
            .apply()
    }

    fun isBiometryNeed() =
        context.getSharedPreferences(AUTH_SHARED_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .getBoolean(SHARED_PREFERENCE_BIOMETRIC_NEEDED, false)

    fun setDarkMode(needed : Boolean) {
        context.getSharedPreferences(DARK_MODE_SHARED_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .edit()
            .putBoolean(SHARED_PREFERENCE_DARK_MODE, needed)
            .apply()
    }

    fun isDarkMode() =
        context.getSharedPreferences(DARK_MODE_SHARED_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .getBoolean(SHARED_PREFERENCE_DARK_MODE, false)

}