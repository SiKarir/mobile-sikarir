package com.c241ps294.sikarir.ui.home

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.ViewSwitcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.databinding.ActivityMainBinding
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModel
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModelFactory
import com.c241ps294.sikarir.ui.catalog.CatalogActivity
import com.c241ps294.sikarir.ui.quiz.starter.QuizStarterActivity
import com.c241ps294.sikarir.ui.settings.SettingPreferences
import com.c241ps294.sikarir.ui.settings.SettingsActivity
import com.c241ps294.sikarir.ui.settings.dataStore
import com.c241ps294.sikarir.ui.settings.viewmodel.ThemeViewModel
import com.c241ps294.sikarir.ui.settings.viewmodel.ThemeViewModelFactory
import com.c241ps294.sikarir.ui.welcome.WelcomeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewSwitcher: ViewSwitcher
    private var isQuizTaken: Boolean = true
    private val authenticationViewModel by viewModels<AuthenticationViewModel> {
        AuthenticationViewModelFactory.getInstance(context = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        authenticationViewModel.getSession().observe(this) {
            if (!it.isLogin) {
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
            }
            else {
                isQuizTaken = it.isTakenQuiz
                setGreeting(it.name)
            }
        }

        viewSwitcher = binding.viewSwitcher
        if (isQuizTaken) {
            viewSwitcher.displayedChild = 1
        } else {
            viewSwitcher.displayedChild = 0;
        }

        bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.selectedItemId = R.id.home_page
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_page -> {
                    true
                }
                R.id.catalog_page -> {
                    val intent = Intent(this, CatalogActivity::class.java)
                    startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
                    true
                }
                R.id.quiz_page -> {
                    val intent = Intent(this, QuizStarterActivity::class.java)
                    startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
                    true
                }
                R.id.settings_page -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
                    true
                }
                else -> false
            }
        }

        binding.btnLihatSemua.setOnClickListener{ navToCatalog() }
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(application.dataStore)
        val themeViewModel = ViewModelProvider(this, ThemeViewModelFactory(pref)).get(
            ThemeViewModel::class.java
        )
        themeViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun setGreeting(name: String) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val greeting = when {
            hour in 0..11 -> "Selamat Pagi, %s!"
            hour in 12..15 -> "Selamat Siang, %s!"
            hour in 16..18 -> "Selamat Sore, %s!"
            else -> "Selamat Malam, %s!"
        }
        binding.tvGreeting.text = String.format(greeting, name)
    }

    private fun navToCatalog() {
        val intent = Intent(this, CatalogActivity::class.java)
        startActivity(intent)
    }
}