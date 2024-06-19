package com.c241ps294.sikarir.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.c241ps294.sikarir.databinding.ActivityWelcomeBinding
import com.c241ps294.sikarir.ui.authentication.AuthenticationActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)

        binding.button.setOnClickListener{ navToAuthPage() }
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        })
    }

    private fun navToAuthPage() {
        val intent = Intent(this, AuthenticationActivity::class.java)
        startActivity(intent)
    }
}