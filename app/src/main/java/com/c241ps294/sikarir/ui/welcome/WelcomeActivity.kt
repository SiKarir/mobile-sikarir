package com.c241ps294.sikarir.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.databinding.ActivityWelcomeBinding
import com.c241ps294.sikarir.ui.authentication.AuthenticationActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)

        binding.button.setOnClickListener{ navToAuthPage() }
        setContentView(binding.root)
    }

    private fun navToAuthPage() {
        val intent = Intent(this, AuthenticationActivity::class.java)
        startActivity(intent)
    }
}