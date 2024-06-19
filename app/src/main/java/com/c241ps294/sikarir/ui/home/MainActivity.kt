package com.c241ps294.sikarir.ui.home

import android.app.ActivityOptions
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.ViewSwitcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.databinding.ActivityMainBinding
import com.c241ps294.sikarir.ui.adapter.MajorListAdapter
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModel
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModelFactory
import com.c241ps294.sikarir.ui.catalog.CatalogActivity
import com.c241ps294.sikarir.ui.home.viewmodel.MainViewModel
import com.c241ps294.sikarir.ui.home.viewmodel.MainViewModelFactory
import com.c241ps294.sikarir.ui.quiz.starter.QuizStarterActivity
import com.c241ps294.sikarir.data.preference.SettingPreferences
import com.c241ps294.sikarir.ui.settings.SettingsActivity
import com.c241ps294.sikarir.data.preference.dataStore
import com.c241ps294.sikarir.data.remote.response.ListCareerItem
import com.c241ps294.sikarir.ui.catalog.career.DetailCareerActivity
import com.c241ps294.sikarir.ui.settings.account.AccountActivity
import com.c241ps294.sikarir.ui.settings.viewmodel.ThemeViewModel
import com.c241ps294.sikarir.ui.settings.viewmodel.ThemeViewModelFactory
import com.c241ps294.sikarir.ui.welcome.WelcomeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewSwitcher: ViewSwitcher
    private lateinit var majorListAdapter: MajorListAdapter
    private lateinit var career: ListCareerItem

    private val authenticationViewModel by viewModels<AuthenticationViewModel> {
        AuthenticationViewModelFactory.getInstance(context = this)
    }

    private val mainViewModel by viewModels<MainViewModel> {
        MainViewModelFactory.getInstance(context = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        majorListAdapter = MajorListAdapter()

        binding.rvMajor.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = majorListAdapter
        }

        authenticationViewModel.getSession().observe(this) {
            if (!it.isLogin) {
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
            }
            else {
                viewSwitcher = binding.viewSwitcher
                if (!it.isTakenQuiz) {
                    viewSwitcher.displayedChild = 1
                } else {
                    viewSwitcher.displayedChild = 0
                    mainViewModel.getQuizHistory(it.token)
                    mainViewModel.quizzes.observe(this) { quiz ->
                        career =  quiz[quiz.size-1].recommendation[0]
                        binding.tvTitle.text = quiz[quiz.size-1].recommendation[0].name
                        binding.tvDescription.text = quiz[quiz.size-1].recommendation[0].description
                    }
                }

                setGreeting(it.username)
                Glide.with(this).load(it.photoUrl).into(binding.ivAvatarAccount)
                mainViewModel.majors.observe(this) { item ->
                    majorListAdapter.submitNonPaginatedList(item)
                }
            }
        }

        mainViewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
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
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
                    true
                }
                R.id.quiz_page -> {
                    val intent = Intent(this, QuizStarterActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
                    true
                }
                R.id.settings_page -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
                    true
                }
                else -> false
            }
        }

        binding.cardView.setOnClickListener{
            val intent = Intent(this, DetailCareerActivity::class.java)
            intent.putExtra(DetailCareerActivity.EXTRA_DATA, career)
            startActivity(intent)
        }

        binding.btnTakeQuiz.setOnClickListener{ navToQuiz() }

        binding.ivAvatarAccount.setOnClickListener{ navToAccount() }

        binding.btnLihatSemua.setOnClickListener{ navToCatalog() }
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(dataStore)
        val themeViewModel = ViewModelProvider(this, ThemeViewModelFactory(pref))[ThemeViewModel::class.java]
        themeViewModel.getThemeSettings().observe(this
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        val sharedPref = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        val languageCode = sharedPref.getString("languageCode", "id") ?: "id"
        setLocale(languageCode)
    }

    private fun navToAccount() {
        val intent = Intent(this, AccountActivity::class.java)
        startActivity(intent)
    }

    private fun navToQuiz() {
        val intent = Intent(this, QuizStarterActivity::class.java)
        startActivity(intent)
    }

    private fun setGreeting(username: String) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val greeting = when {
            hour in 0..11 -> getString(R.string.selamat_pagi_s)
            hour in 12..15 -> getString(R.string.selamat_siang_s)
            hour in 16..18 -> getString(R.string.selamat_sore_s)
            else -> getString(R.string.selamat_malam_s)
        }
        binding.tvGreeting.text = String.format(greeting, username)
    }

    private fun navToCatalog() {
        val intent = Intent(this, CatalogActivity::class.java)
        startActivity(intent)
    }
    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration().apply {
            setLocale(locale)
        }
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
    }
}