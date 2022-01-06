package com.cwi.matheus.pokeapp.presentation.about

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.cwi.matheus.pokeapp.R
import com.cwi.matheus.pokeapp.databinding.ActivityAboutBinding
import com.cwi.matheus.pokeapp.presentation.base.BaseBottomNavigationActivity
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
    }
}