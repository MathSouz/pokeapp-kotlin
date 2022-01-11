package com.cwi.matheus.pokeapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.cwi.matheus.pokeapp.base.SharedPreferencesManager
import com.cwi.matheus.pokeapp.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreate() {
        super.onCreate()

        sharedPreferencesManager = SharedPreferencesManager(this)

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(appModules)
        }

        if(sharedPreferencesManager.isDarkMode()) {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        }
    }
}