package com.cwi.matheus.pokeapp.presentation.start

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.base.SharedPreferencesManager
import com.cwi.matheus.pokeapp.databinding.ActivityStartBinding
import com.cwi.matheus.pokeapp.presentation.pokemon.PokemonHostActivity

class StartActivity : AppCompatActivity() {
    private lateinit var binding : ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callBiometricAuth()
    }

    private fun callBiometricAuth() {
        val sharedPreferencesManager = SharedPreferencesManager(this)

        if(sharedPreferencesManager.isBiometryNeed()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                showBiometricPrompt()
            }
        } else {
            sendToMainActivity()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun showBiometricPrompt() {

        val executor = ContextCompat.getMainExecutor(this)
        val biometricAuthenticationText = this.getString(R.string.txt_biometric_authentication)
        val biometryAlertText = this.getString(R.string.txt_biometry_alert)
        val cancel = this.getString(R.string.txt_cancel)

        val biometricPrompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
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
        finishAndRemoveTask()
    }

    private fun onBiometricAuthenticationSuccess() {
        sendToMainActivity()
    }

    private fun sendToMainActivity() {
        val intent = Intent(this, PokemonHostActivity::class.java)
        startActivity(intent)
    }
}