package com.cwi.matheus.pokeapp.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cwi.matheus.pokeapp.data.sharedPreferences.SharedPreferencesRepositoryImpl
import com.cwi.matheus.pokeapp.service.BiometryService

abstract class BaseSecureByBiometryActivity : AppCompatActivity() {

    private lateinit var biometryService: BiometryService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        biometryService = BiometryService(
            this,
            onAuthenticationFail = { onAuthFail() },
            onAuthenticationSuccess = { onAuthSuccess() },
            onAuthenticationNotNeeded = { onAuthNotNeeded() },
            sharedPreferencesRepositoryImpl = SharedPreferencesRepositoryImpl(this)
        )
        biometryService.callBiometricAuth()
    }

    abstract fun isSecured(): Boolean

    open fun onAuthFail() {
        onBackPressed()
    }

    open fun onAuthSuccess() {}

    open fun onAuthNotNeeded() {}
}