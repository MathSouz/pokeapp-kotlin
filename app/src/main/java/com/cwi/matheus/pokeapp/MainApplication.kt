package com.cwi.matheus.pokeapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.cwi.matheus.pokeapp.base.DARK_MODE_SHARED_PREFERENCES
import com.cwi.matheus.pokeapp.base.SHARED_PREFERENCE_DARK_MODE
import com.cwi.matheus.pokeapp.base.SharedPreferencesManager
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

        if(SharedPreferencesRepositoryImpl(this).isDarkMode()) {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        }
    }
}