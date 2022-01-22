package com.cwi.matheus.pokeapp.presentation.preferences

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.CompoundButton
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.base.SharedPreferencesManager
import com.cwi.matheus.pokeapp.databinding.ActivityPreferencesBinding
import com.cwi.matheus.pokeapp.presentation.base.BaseBottomNavigationActivity
import com.cwi.matheus.pokeapp.presentation.preferences.viewModel.PreferencesViewModel
import com.cwi.matheus.pokeapp.service.BiometryUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class PreferencesActivity : BaseBottomNavigationActivity() {

    private lateinit var binding: ActivityPreferencesBinding
    override val tab: Int = R.id.menu_preferences
    private val viewModel : PreferencesViewModel by viewModel()

    override fun getBottomNavigation(): BottomNavigationView =
        binding.viewBottomNavigation.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvPokeapi.movementMethod = LinkMovementMethod.getInstance()
        binding.tvGithub.movementMethod = LinkMovementMethod.getInstance()
        binding.tvLicenseLink.movementMethod = LinkMovementMethod.getInstance()
        setupBiometricAuthSwitch()
        setupDarkModeSwitch()
    }

    private fun setupDarkModeSwitch() {
        val onCheckListener = CompoundButton.OnCheckedChangeListener { _, checked ->
            viewModel.setDarkMode(checked)
        }

        binding.switchDarkMode.isChecked = viewModel.isDarkMode()
        binding.switchDarkMode.setOnCheckedChangeListener(onCheckListener)
    }

    private fun setupBiometricAuthSwitch() {

        val onCheckListener = CompoundButton.OnCheckedChangeListener { _, checked ->
            viewModel.setBiometryNeedPreference(checked)
        }

        binding.switchAuth.isChecked = viewModel.isBiometryNeed()

        if (BiometryUtils.existsBiometryService(this)) {
            binding.switchAuth.isEnabled = true
            binding.switchAuth.setOnCheckedChangeListener(onCheckListener)
        } else {
            binding.switchAuth.isEnabled = false
        }
    }
}