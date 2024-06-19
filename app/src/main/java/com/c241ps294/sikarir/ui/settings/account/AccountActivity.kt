package com.c241ps294.sikarir.ui.settings.account

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.databinding.ActivityAccountBinding
import com.c241ps294.sikarir.ui.adapter.QuizHistoryAdapter
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModel
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModelFactory
import com.c241ps294.sikarir.ui.catalog.CatalogActivity
import com.c241ps294.sikarir.ui.home.MainActivity
import com.c241ps294.sikarir.ui.quiz.starter.QuizStarterActivity
import com.c241ps294.sikarir.ui.settings.viewmodel.AccountViewModel
import com.c241ps294.sikarir.ui.settings.viewmodel.AccountViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var quizHistoryAdapter: QuizHistoryAdapter
    private val authenticationViewModel by viewModels<AuthenticationViewModel> {
        AuthenticationViewModelFactory.getInstance(context = this)
    }
    private val accountViewModel by viewModels<AccountViewModel> {
        AccountViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)

        quizHistoryAdapter = QuizHistoryAdapter()
        binding.rvQuizHistory.layoutManager = LinearLayoutManager(this)
        binding.rvQuizHistory.adapter = quizHistoryAdapter

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

        binding.btnEditAccount.setOnClickListener {
            startActivity(Intent(this, EditAccountActivity::class.java))
        }

        authenticationViewModel.getSession().observe(this) { session ->
            Glide.with(this).load(session.photoUrl).into(binding.ivAvatarAccount)
            binding.tvNameAccount.text = session.name
            binding.tvEmailAccount.text = session.email
            if (session.isTakenQuiz) {
                binding.rvQuizHistory.visibility = View.VISIBLE
                binding.quizLayout.visibility = View.GONE

                accountViewModel.getQuizHistory(session.token)
                accountViewModel.quizzes.observe(this) { quizzes ->
                    quizHistoryAdapter.submitList(quizzes)
                }
            } else {
                binding.quizLayout.visibility = View.VISIBLE
                binding.rvQuizHistory.visibility = View.GONE

                binding.btnTakeQuiz.setOnClickListener {
                    startActivity(Intent(this, QuizStarterActivity::class.java))
                }
            }
        }
        setContentView(binding.root)
    }
}