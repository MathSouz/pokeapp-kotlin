package com.cwi.matheus.pokeapp.service

import android.content.Context
import androidx.biometric.BiometricManager

class BiometryService(
    private val context : Context) {

    fun existsBiometryService() : Boolean {
        val biometricManager = BiometricManager.from(context)
        val canAuthenticateType = biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)
        return canAuthenticateType == BiometricManager.BIOMETRIC_SUCCESS
    }
}