package com.c241ps294.sikarir.ui.quiz.questions

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.c241ps294.sikarir.data.local.quiz.AbstractReasoning
import com.c241ps294.sikarir.databinding.ActivityAbstractReasoningBinding
import com.c241ps294.sikarir.ui.adapter.AbstractReasoningAnswerAdapter

class AbstractReasoningActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAbstractReasoningBinding
    private lateinit var adapter: AbstractReasoningAnswerAdapter
    private var questionPointer = 0
    private val questions = listOf(
        AbstractReasoning(
            no = 71,
            questionText = "Pola mana yang dapat menggantikan tandanya?",
            imageUrl = "https://sikarir.github.io/image-quiz-storage/ar_1.jpg",
            option1 = "https://sikarir.github.io/image-quiz-storage/ar_1_a.jpg",
            option2 = "https://sikarir.github.io/image-quiz-storage/ar_1_b.jpg",
            option3 = "https://sikarir.github.io/image-quiz-storage/ar_1_c.jpg",
            option4 = "https://sikarir.github.io/image-quiz-storage/ar_1_d.jpg",
            option5 = "https://sikarir.github.io/image-quiz-storage/ar_1_e.jpg"
        ),
        AbstractReasoning(
            no = 72,
            questionText = "Pola mana yang dapat menggantikan tandanya?",
            imageUrl = "https://sikarir.github.io/image-quiz-storage/ar_2.jpg",
            option1 = "https://sikarir.github.io/image-quiz-storage/ar_2_a.jpg",
            option2 = "https://sikarir.github.io/image-quiz-storage/ar_2_b.jpg",
            option3 = "https://sikarir.github.io/image-quiz-storage/ar_2_c.jpg",
            option4 = "https://sikarir.github.io/image-quiz-storage/ar_2_d.jpg",
            option5 = "https://sikarir.github.io/image-quiz-storage/ar_2_e.jpg"
        ),
        AbstractReasoning(
            no = 73,
            questionText = "Pola mana yang dapat menggantikan tandanya?",
            imageUrl = "https://sikarir.github.io/image-quiz-storage/ar_3.jpg",
            option1 = "https://sikarir.github.io/image-quiz-storage/ar_3_a.jpg",
            option2 = "https://sikarir.github.io/image-quiz-storage/ar_3_b.jpg",
            option3 = "https://sikarir.github.io/image-quiz-storage/ar_3_c.jpg",
            option4 = "https://sikarir.github.io/image-quiz-storage/ar_3_d.jpg",
            option5 = "https://sikarir.github.io/image-quiz-storage/ar_3_e.jpg"
        ),
//        AbstractReasoning(
//            no = 74,
//            questionText = "Pola mana yang dapat menggantikan tandanya?",
//            imageUrl = "https://sikarir.github.io/image-quiz-storage/ar_4.jpg",
//            option1 = "https://sikarir.github.io/image-quiz-storage/ar_4_a.jpg",
//            option2 = "https://sikarir.github.io/image-quiz-storage/ar_4_b.jpg",
//            option3 = "https://sikarir.github.io/image-quiz-storage/ar_4_c.jpg",
//            option4 = "https://sikarir.github.io/image-quiz-storage/ar_4_d.jpg",
//            option5 = "https://sikarir.github.io/image-quiz-storage/ar_4_e.jpg"
//        ),
//        AbstractReasoning(
//            no = 75,
//            questionText = "Pola mana yang dapat menggantikan tandanya?",
//            imageUrl = "https://sikarir.github.io/image-quiz-storage/ar_5.jpg",
//            option1 = "https://sikarir.github.io/image-quiz-storage/ar_5_a.jpg",
//            option2 = "https://sikarir.github.io/image-quiz-storage/ar_5_b.jpg",
//            option3 = "https://sikarir.github.io/image-quiz-storage/ar_5_c.jpg",
//            option4 = "https://sikarir.github.io/image-quiz-storage/ar_5_d.jpg",
//            option5 = "https://sikarir.github.io/image-quiz-storage/ar_5_e.jpg"
//        ),
//        AbstractReasoning(
//            no = 76,
//            questionText = "Pola mana yang dapat menggantikan tandanya?",
//            imageUrl = "https://sikarir.github.io/image-quiz-storage/ar_6.jpg",
//            option1 = "https://sikarir.github.io/image-quiz-storage/ar_6_a.jpg",
//            option2 = "https://sikarir.github.io/image-quiz-storage/ar_6_b.jpg",
//            option3 = "https://sikarir.github.io/image-quiz-storage/ar_6_c.jpg",
//            option4 = "https://sikarir.github.io/image-quiz-storage/ar_6_d.jpg",
//            option5 = "https://sikarir.github.io/image-quiz-storage/ar_6_e.jpg"
//        ),
//        AbstractReasoning(
//            no = 77,
//            questionText = "Pola mana yang dapat menggantikan tandanya?",
//            imageUrl = "https://sikarir.github.io/image-quiz-storage/ar_7.jpg",
//            option1 = "https://sikarir.github.io/image-quiz-storage/ar_7_a.jpg",
//            option2 = "https://sikarir.github.io/image-quiz-storage/ar_7_b.jpg",
//            option3 = "https://sikarir.github.io/image-quiz-storage/ar_7_c.jpg",
//            option4 = "https://sikarir.github.io/image-quiz-storage/ar_7_d.jpg",
//            option5 = "https://sikarir.github.io/image-quiz-storage/ar_7_e.jpg"
//        ),
//        AbstractReasoning(
//            no = 78,
//            questionText = "Pola mana yang dapat menggantikan tandanya?",
//            imageUrl = "https://sikarir.github.io/image-quiz-storage/ar_8.jpg",
//            option1 = "https://sikarir.github.io/image-quiz-storage/ar_8_a.jpg",
//            option2 = "https://sikarir.github.io/image-quiz-storage/ar_8_b.jpg",
//            option3 = "https://sikarir.github.io/image-quiz-storage/ar_8_c.jpg",
//            option4 = "https://sikarir.github.io/image-quiz-storage/ar_8_d.jpg",
//            option5 = "https://sikarir.github.io/image-quiz-storage/ar_8_e.jpg"
//        ),
//        AbstractReasoning(
//            no = 79,
//            questionText = "Pola mana yang dapat menggantikan tandanya?",
//            imageUrl = "https://sikarir.github.io/image-quiz-storage/ar_9.jpg",
//            option1 = "https://sikarir.github.io/image-quiz-storage/ar_9_a.jpg",
//            option2 = "https://sikarir.github.io/image-quiz-storage/ar_9_b.jpg",
//            option3 = "https://sikarir.github.io/image-quiz-storage/ar_9_c.jpg",
//            option4 = "https://sikarir.github.io/image-quiz-storage/ar_9_d.jpg",
//            option5 = "https://sikarir.github.io/image-quiz-storage/ar_9_e.jpg"
//        ),
//        AbstractReasoning(
//            no = 80,
//            questionText = "Pola mana yang dapat menggantikan tandanya?",
//            imageUrl = "https://sikarir.github.io/image-quiz-storage/ar_10.jpg",
//            option1 = "https://sikarir.github.io/image-quiz-storage/ar_10_a.jpg",
//            option2 = "https://sikarir.github.io/image-quiz-storage/ar_10_b.jpg",
//            option3 = "https://sikarir.github.io/image-quiz-storage/ar_10_c.jpg",
//            option4 = "https://sikarir.github.io/image-quiz-storage/ar_10_d.jpg",
//            option5 = "https://sikarir.github.io/image-quiz-storage/ar_10_e.jpg"
//        ),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbstractReasoningBinding.inflate(layoutInflater)

        setContentView(binding.root)

        bindViews()
        setUpEventListener()
    }

    private fun setUpEventListener() {
        binding.btnNext.setOnClickListener {
            if (questionPointer < questions.size - 1) {
                questionPointer++
                bindViews()
            } else {
                val intent = Intent(this, VerbalReasoningActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
            }
        }

        binding.btnPrevious.setOnClickListener {
            if (questionPointer > 0) {
                questionPointer--
                bindViews()
            } else {
                val intent = Intent(this, SpatialAptitudeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
            }
        }
    }

    private fun bindViews() {
        binding.btnNext.visibility = View.VISIBLE
        binding.btnPrevious.visibility = View.VISIBLE

        val question = questions[questionPointer]
        question.let {
            Glide.with(this).load(question.imageUrl).into(binding.ivQuizImage)
            binding.tvQuizQuestions.text = it.questionText
            adapter = AbstractReasoningAnswerAdapter(question)
            binding.recyclerViewOptions.adapter = adapter
            binding.recyclerViewOptions.layoutManager = LinearLayoutManager(this)
        }
    }
}