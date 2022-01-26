package com.cwi.matheus.pokeapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.cwi.matheus.pokeapp.base.SHARED_PREFERENCE_DARK_MODE
import com.cwi.matheus.pokeapp.data.sharedPreferences.SharedPreferencesRepositoryImpl
import com.cwi.matheus.pokeapp.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(appModules)
        }

        setupDarkMode()
    }

    private fun setupDarkMode() {
        val sharedPreferences = SharedPreferencesRepositoryImpl(this)

        val defaultDarkModeState = true

        if (!sharedPreferences.contains(SHARED_PREFERENCE_DARK_MODE)) {
            sharedPreferences.writeBoolean(SHARED_PREFERENCE_DARK_MODE, defaultDarkModeState)
        }

        val darkMode =
            sharedPreferences.readBoolean(SHARED_PREFERENCE_DARK_MODE, defaultDarkModeState)

        setDarkMode(darkMode)
    }

    private fun setDarkMode(darkMode: Boolean) {
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        }
    }
}