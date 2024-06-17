package com.c241ps294.sikarir.ui.settings.account

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.databinding.ActivityAccountBinding
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModel
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModelFactory
import com.c241ps294.sikarir.ui.catalog.CatalogActivity
import com.c241ps294.sikarir.ui.home.MainActivity
import com.c241ps294.sikarir.ui.quiz.starter.QuizStarterActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private val authenticationViewModel by viewModels<AuthenticationViewModel> {
        AuthenticationViewModelFactory.getInstance(context = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
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

        binding.btnEditAccount.setOnClickListener {
            startActivity(Intent(this, EditAccountActivity::class.java))
        }

        authenticationViewModel.getSession().observe(this) {
            Glide.with(this).load(it.photoUrl).into(binding.ivAvatarAccount)
            binding.tvNameAccount.text = it.name
            binding.tvEmailAccount.text = it.email
        }

        setContentView(binding.root)
    }
}