package com.c241ps294.sikarir.ui.quiz.starter

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.databinding.ActivityQuizStarterBinding
import com.c241ps294.sikarir.ui.catalog.CatalogActivity
import com.c241ps294.sikarir.ui.home.MainActivity
import com.c241ps294.sikarir.ui.quiz.questions.QuizQuestionsActivity
import com.c241ps294.sikarir.ui.settings.SettingsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class QuizStarterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizStarterBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizStarterBinding.inflate(layoutInflater)
        bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.selectedItemId = R.id.quiz_page

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.quiz_page -> {
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
                R.id.settings_page -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
                    true
                }
                else -> false
            }
        }

        binding.quizButton.setOnClickListener { takeAQuiz() }
        setContentView(binding.root)
    }

    private fun takeAQuiz() {
        val intent = Intent(this, QuizQuestionsActivity::class.java)
        startActivity(intent)
    }
}