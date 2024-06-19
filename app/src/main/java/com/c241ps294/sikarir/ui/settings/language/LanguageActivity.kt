package com.c241ps294.sikarir.ui.settings.language

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.databinding.ActivityLanguageBinding
import com.c241ps294.sikarir.ui.catalog.CatalogActivity
import com.c241ps294.sikarir.ui.home.MainActivity
import com.c241ps294.sikarir.ui.quiz.starter.QuizStarterActivity
import com.c241ps294.sikarir.ui.settings.SettingsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class LanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.selectedItemId = R.id.settings_page

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.settings_page -> {
                    true
                }
                R.id.catalog_page -> {
                    val intent = Intent(this, CatalogActivity::class.java)
                    startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
                    true
                }
                R.id.home_page -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
                    true
                }
                R.id.quiz_page -> {
                    val intent = Intent(this, QuizStarterActivity::class.java)
                    startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
                    true
                }
                else -> false
            }
        }

        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        var languageCode = sharedPref.getString("languageCode", "id")

        when (languageCode) {
            "id" -> binding.radioButtonIndonesia.isChecked = true
            "en" -> binding.radioButtonEnglish.isChecked = true
        }

        binding.btnChooseLanguage.setOnClickListener {
            languageCode = when (binding.radioGroupLanguage.checkedRadioButtonId) {
                R.id.radioButtonIndonesia -> "id"
                R.id.radioButtonEnglish -> "en"
                else -> return@setOnClickListener
            }
            setLocale(languageCode!!)
        }
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        with(getPreferences(Context.MODE_PRIVATE).edit()) {
            putString("languageCode", languageCode)
            apply()
        }

        recreate()
        startActivity(Intent(this, SettingsActivity::class.java))
    }
}
