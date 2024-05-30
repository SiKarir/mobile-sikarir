package com.c241ps294.sikarir.ui.quiz.questions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.c241ps294.sikarir.databinding.ActivityQuizQuestionsBinding
import com.c241ps294.sikarir.ui.quiz.result.QuizResultActivity

class QuizQuestionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizQuestionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)

        binding.btnNext.setOnClickListener{ quizResult() }
        setContentView(binding.root)
    }

    private fun quizResult() {
        val intent = Intent(this, QuizResultActivity::class.java)
        startActivity(intent)
    }
}