package com.cwi.matheus.pokeapp.service

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.base.SHARED_PREFERENCE_BIOMETRIC_NEEDED
import com.cwi.matheus.pokeapp.data.sharedPreferences.SharedPreferencesRepositoryImpl

class BiometryService(
    private val activity: AppCompatActivity,
    private val sharedPreferencesRepositoryImpl: SharedPreferencesRepositoryImpl,
    private val onAuthenticationSuccess: () -> Unit,
    private val onAuthenticationFail: () -> Unit,
    private val onAuthenticationNotNeeded: () -> Unit
) {

    fun callBiometricAuth() {

        val biometry =
            sharedPreferencesRepositoryImpl.readBoolean(SHARED_PREFERENCE_BIOMETRIC_NEEDED, false)

        if (biometry) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                showBiometricPrompt()
            }
        } else {
            onAuthenticationNotNeeded()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun showBiometricPrompt() {

        val executor = ContextCompat.getMainExecutor(activity)
        val biometricAuthenticationText = activity
            .getString(R.string.txt_biometric_authentication)
        val biometryAlertText = activity.getString(R.string.txt_biometry_alert)
        val cancel = activity.getString(R.string.txt_cancel)

        val biometricPrompt = BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    onBiometricAuthenticationFailed()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
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