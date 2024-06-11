package com.c241ps294.sikarir.ui.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.databinding.ActivityAuthenticationBinding
import com.c241ps294.sikarir.ui.authentication.login.LoginFragment

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)

        if (savedInstanceState == null) {
            fragmentSetup()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        })

        setContentView(binding.root)
    }

    private fun fragmentSetup() {
        supportFragmentManager.beginTransaction().add(R.id.authentication_container, LoginFragment()).commit()
    }
}