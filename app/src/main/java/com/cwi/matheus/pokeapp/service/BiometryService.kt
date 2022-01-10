package com.cwi.matheus.pokeapp.service

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.base.SharedPreferencesManager
import com.cwi.matheus.pokeapp.presentation.pokemon.PokemonHostActivity
import com.cwi.matheus.pokeapp.presentation.start.StartActivity

class BiometryService(
    private val startActivity: AppCompatActivity,
    private val onAuthenticationSuccess : () -> Unit,
    private val onAuthenticationFail : () -> Unit,
    private val onAuthenticationNotNeeded : () -> Unit) {

    fun callBiometricAuth() {
        val sharedPreferencesManager = SharedPreferencesManager(startActivity)

        if(sharedPreferencesManager.isBiometryNeed()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                showBiometricPrompt()
            }
        } else {
            onAuthenticationNotNeeded()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun showBiometricPrompt() {

        val executor = ContextCompat.getMainExecutor(startActivity)
        val biometricAuthenticationText = startActivity
            .getString(R.string.txt_biometric_authentication)
        val biometryAlertText = startActivity.getString(R.string.txt_biometry_alert)
        val cancel = startActivity.getString(R.string.txt_cancel)

        val biometricPrompt = BiometricPrompt(
            startActivity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int,
                                               errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                onBiometricAuthenticationFailed()
            }

            override fun onAuthenticationSucceeded(
                result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                onBiometricAuthenticationSuccess()
            }
        })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(biometricAuthenticationText)
            .setSubtitle(biometryAlertText)
            .setNegativeButtonText(cancel)
            .build()
        biometricPrompt.authenticate(promptInfo)
    }

    private fun onBiometricAuthenticationFailed() {
        onAuthenticationFail()
    }

    private fun onBiometricAuthenticationSuccess() {
        onAuthenticationSuccess()
    }


}