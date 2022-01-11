package com.cwi.matheus.pokeapp.service

import android.content.Context
import androidx.biometric.BiometricManager

class BiometryUtils {

    companion object {
        fun existsBiometryService(context : Context) : Boolean {
            val biometricManager = BiometricManager.from(context)
            val canAuthenticateType = biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)
            return canAuthenticateType == BiometricManager.BIOMETRIC_SUCCESS
        }
    }
}