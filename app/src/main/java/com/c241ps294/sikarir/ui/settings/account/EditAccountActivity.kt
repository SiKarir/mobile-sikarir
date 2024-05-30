package com.c241ps294.sikarir.ui.settings.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.databinding.ActivityEditAccountBinding

class EditAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}