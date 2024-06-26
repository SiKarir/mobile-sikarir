package com.c241ps294.sikarir.ui.settings

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.data.preference.SettingPreferences
import com.c241ps294.sikarir.data.preference.dataStore
import com.c241ps294.sikarir.databinding.ActivitySettingsBinding
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModel
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModelFactory
import com.c241ps294.sikarir.ui.catalog.CatalogActivity
import com.c241ps294.sikarir.ui.home.MainActivity
import com.c241ps294.sikarir.ui.quiz.starter.QuizStarterActivity
import com.c241ps294.sikarir.ui.settings.account.AccountActivity
import com.c241ps294.sikarir.ui.settings.account.EditAccountActivity
import com.c241ps294.sikarir.ui.settings.language.LanguageActivity
import com.c241ps294.sikarir.ui.settings.viewmodel.ThemeViewModel
import com.c241ps294.sikarir.ui.settings.viewmodel.ThemeViewModelFactory
import com.c241ps294.sikarir.ui.welcome.WelcomeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private val authenticationViewModel by viewModels<AuthenticationViewModel> {
        AuthenticationViewModelFactory.getInstance(context = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)

        bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.selectedItemId = R.id.settings_page

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.settings_page -> {
                    true
                }
                R.id.catalog_page -> {
                    val intent = Intent(this, CatalogActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
                    true
                }
                R.id.home_page -> {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
                    true
                }
                R.id.quiz_page -> {
                    val intent = Intent(this, QuizStarterActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
                    true
                }
                else -> false
            }
        }

        binding.arrowButtonLogout.setOnClickListener {
            logout()
        }

        binding.btnEditAccount.setOnClickListener {
            startActivity(Intent(this, EditAccountActivity::class.java))
        }

        binding.arrowButtonAkun.setOnClickListener {
            startActivity(Intent(this, AccountActivity::class.java))
        }

        binding.arrowButtonBahasa.setOnClickListener {
            startActivity(Intent(this, LanguageActivity::class.java))
        }

        authenticationViewModel.getSession().observe(this) {
            Glide.with(this).load(it.photoUrl).into(binding.ivAvatarAccount)
            binding.tvNameAccount.text = it.name
            binding.tvEmailAccount.text = it.email
        }

        setContentView(binding.root)

        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)

        val pref = SettingPreferences.getInstance(application.dataStore)

        val themeViewModel = ViewModelProvider(this, ThemeViewModelFactory(pref))[ThemeViewModel::class.java]

        themeViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            switchTheme.isChecked = isDarkModeActive
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            themeViewModel.saveThemeSetting(isChecked)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        })
    }

    private fun logout() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.logout_title)
        builder.setMessage(R.string.confirm_logout)
        builder.setPositiveButton(R.string.confirmed_logout) { _, _ ->
            authenticationViewModel.logout()
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
        builder.setNegativeButton(R.string.not_logout) { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}