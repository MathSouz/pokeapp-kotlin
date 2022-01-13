package com.cwi.matheus.pokeapp.presentation.start

import android.content.Intent
import android.os.Bundle
import com.cwi.matheus.pokeapp.databinding.ActivityStartBinding
import com.cwi.matheus.pokeapp.presentation.base.BaseSecureByBiometryActivity
import com.cwi.matheus.pokeapp.presentation.pokemon.PokemonHostActivity

class StartActivity : BaseSecureByBiometryActivity() {
    private lateinit var binding : ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun isSecured(): Boolean = true

    private fun sendToMainActivity() {
        val intent = Intent(this, PokemonHostActivity::class.java)
        this.startActivity(intent)
    }

    override fun onAuthSuccess() {
        super.onAuthSuccess()
        sendToMainActivity()
    }

    override fun onAuthNotNeeded() {
        super.onAuthNotNeeded()
        sendToMainActivity()
    }
}