package com.cwi.matheus.pokeapp.presentation.about

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.CompoundButton
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.base.SharedPreferencesManager
import com.cwi.matheus.pokeapp.databinding.ActivityAboutBinding
import com.cwi.matheus.pokeapp.presentation.base.BaseBottomNavigationActivity
import com.cwi.matheus.pokeapp.service.BiometryUtils
import com.google.android.material.bottomnavigation.BottomNavigationView

class AboutActivity : BaseBottomNavigationActivity() {

    private lateinit var binding: ActivityAboutBinding
    override val tab: Int = R.id.menu_about

    override fun getBottomNavigation(): BottomNavigationView =
        binding.viewBottomNavigation.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvPokeapi.movementMethod = LinkMovementMethod.getInstance()
        binding.tvGithub.movementMethod = LinkMovementMethod.getInstance()
        binding.tvLicenseLink.movementMethod = LinkMovementMethod.getInstance()

        setupBiometricAuthSwitch()
    }

    private fun setupBiometricAuthSwitch() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val biometryService = BiometryUtils(this)

        val onCheckListener = CompoundButton.OnCheckedChangeListener { _, checked ->
            sharedPreferencesManager.setBiometryNeedPreference(checked)
        }

        binding.switchAuth.isChecked = sharedPreferencesManager.isBiometryNeed()

        if(biometryService.existsBiometryService()) {
            binding.switchAuth.isEnabled = true
            binding.switchAuth.setOnCheckedChangeListener(onCheckListener)
        } else {
            binding.switchAuth.isEnabled = false
        }
    }
}