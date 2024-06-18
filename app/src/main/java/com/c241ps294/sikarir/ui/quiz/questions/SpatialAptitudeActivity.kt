package com.c241ps294.sikarir.ui.quiz.questions

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.data.local.quiz.SpatialAptitude
import com.c241ps294.sikarir.databinding.ActivitySpatialAptitudeBinding
import com.c241ps294.sikarir.ui.adapter.SpatialAptitudeAnswerAdapter

class SpatialAptitudeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpatialAptitudeBinding
    private lateinit var adapter: SpatialAptitudeAnswerAdapter
    private var questionPointer = 0
    private val questions = listOf(
        SpatialAptitude(
            no = 61,
            imageUrl = "https://sikarir.github.io/image-quiz-storage/sa_1.png",
            questionText = "Hitung banyak persegi dari gambar di atasHitung banyak persegi dari gambar di atas",
            option1 = "https://sikarir.github.io/image-quiz-storage/sa_1_a.png",
            option2 = "https://sikarir.github.io/image-quiz-storage/sa_1_b.png",
            option3 = "https://sikarir.github.io/image-quiz-storage/sa_1_c.png",
            option4 = "https://sikarir.github.io/image-quiz-storage/sa_1_d.png",
            option5 = "https://sikarir.github.io/image-quiz-storage/sa_1_e.png"
        ),
        SpatialAptitude(
            no = 62,
            imageUrl = "https://sikarir.github.io/image-quiz-storage/sa_2.png",
            questionText = "Kubus mana yang menunjukkan bentuk di atas dalam kondisi terlipat?",
            option1 = "https://sikarir.github.io/image-quiz-storage/sa_2_a.png",
            option2 = "https://sikarir.github.io/image-quiz-storage/sa_2_b.png",
            option3 = "https://sikarir.github.io/image-quiz-storage/sa_2_c.png",
            option4 = "https://sikarir.github.io/image-quiz-storage/sa_2_d.png",
            option5 = "https://sikarir.github.io/image-quiz-storage/sa_2_e.png"
        ),
        SpatialAptitude(
            no = 63,
            imageUrl = "https://sikarir.github.io/image-quiz-storage/sa_3.png",
            questionText = "Ketika digabungkan, bentuk mana yang akan tercipta. Perhatikan bahwa bagian yang ditandai A harus menempel dengan A yang lain, begitu juga dengan yang lain.",
            option1 = "https://sikarir.github.io/image-quiz-storage/sa_3_a.png",
            option2 = "https://sikarir.github.io/image-quiz-storage/sa_3_b.png",
            option3 = "https://sikarir.github.io/image-quiz-storage/sa_3_c.png",
            option4 = "https://sikarir.github.io/image-quiz-storage/sa_3_d.png",
            option5 = "https://sikarir.github.io/image-quiz-storage/sa_3_e.png"
        ),
        SpatialAptitude(
            no = 64,
            imageUrl = "https://sikarir.github.io/image-quiz-storage/sa_4.png",
            questionText = "Gambar mana yang menunjukkan objek yang sama dengan yang ditampilkan di atas?",
            option1 = "https://sikarir.github.io/image-quiz-storage/sa_4_a.png",
            option2 = "https://sikarir.github.io/image-quiz-storage/sa_4_b.png",
            option3 = "https://sikarir.github.io/image-quiz-storage/sa_4_c.png",
            option4 = "https://sikarir.github.io/image-quiz-storage/sa_4_d.png",
            option5 = "https://sikarir.github.io/image-quiz-storage/sa_4_e.png"
        ),
        SpatialAptitude(
            no = 65,
            imageUrl = "https://sikarir.github.io/image-quiz-storage/sa_5.png",
            questionText = "Kubus mana yang menunjukkan bentuk di atas dalam kondisi terlipat?",
            option1 = "https://sikarir.github.io/image-quiz-storage/sa_5_a.png",
            option2 = "https://sikarir.github.io/image-quiz-storage/sa_5_b.png",
            option3 = "https://sikarir.github.io/image-quiz-storage/sa_5_c.png",
            option4 = "https://sikarir.github.io/image-quiz-storage/sa_5_d.png",
            option5 = "https://sikarir.github.io/image-quiz-storage/sa_5_e.png"
        ),
        SpatialAptitude(
            no = 66,
            imageUrl = "https://sikarir.github.io/image-quiz-storage/sa_6.png",
            questionText = "Ketika digabungkan, bentuk mana yang akan tercipta. Perhatikan bahwa bagian yang ditandai A harus menempel dengan A yang lain, begitu juga dengan yang lain.",
            option1 = "https://sikarir.github.io/image-quiz-storage/sa_6_a.png",
            option2 = "https://sikarir.github.io/image-quiz-storage/sa_6_b.png",
            option3 = "https://sikarir.github.io/image-quiz-storage/sa_6_c.png",
            option4 = "https://sikarir.github.io/image-quiz-storage/sa_6_d.png",
            option5 = "https://sikarir.github.io/image-quiz-storage/sa_6_e.png"
        ),
        SpatialAptitude(
            no = 67,
            imageUrl = "https://sikarir.github.io/image-quiz-storage/sa_7.png",
            questionText = "Pilihlah kubus yang merupakan kubus yang sama dengan kubus di atas!",
            option1 = "https://sikarir.github.io/image-quiz-storage/sa_7_a.png",
            option2 = "https://sikarir.github.io/image-quiz-storage/sa_7_b.png",
            option3 = "https://sikarir.github.io/image-quiz-storage/sa_7_c.png",
            option4 = "https://sikarir.github.io/image-quiz-storage/sa_7_d.png",
            option5 = "https://sikarir.github.io/image-quiz-storage/sa_7_e.png"
        ),
        SpatialAptitude(
            no = 68,
            imageUrl = "https://sikarir.github.io/image-quiz-storage/sa_8.png",
            questionText = "Bentuk mana yang merupakan tampilan kubus tampak atas?",
            option1 = "https://sikarir.github.io/image-quiz-storage/sa_8_a.png",
            option2 = "https://sikarir.github.io/image-quiz-storage/sa_8_b.png",
            option3 = "https://sikarir.github.io/image-quiz-storage/sa_8_c.png",
            option4 = "https://sikarir.github.io/image-quiz-storage/sa_8_d.png",
            option5 = "https://sikarir.github.io/image-quiz-storage/sa_8_e.png"
        ),
        SpatialAptitude(
            no = 69,
            imageUrl = "https://sikarir.github.io/image-quiz-storage/sa_9.png",
            questionText = "Kubus mana yang menunjukkan bentuk di atas dalam kondisi terlipat?",
            option1 = "https://sikarir.github.io/image-quiz-storage/sa_9_a.png",
            option2 = "https://sikarir.github.io/image-quiz-storage/sa_9_b.png",
            option3 = "https://sikarir.github.io/image-quiz-storage/sa_9_c.png",
            option4 = "https://sikarir.github.io/image-quiz-storage/sa_9_d.png",
            option5 = "https://sikarir.github.io/image-quiz-storage/sa_9_e.png"
        ),
        SpatialAptitude(
            no = 70,
            imageUrl = "https://sikarir.github.io/image-quiz-storage/sa_10.png",
            questionText = "Gambar manakah yang memiliki bentuk 3D yang sama dengan di atas, tetapi pada posisi yang berbeda?",
            option1 = "https://sikarir.github.io/image-quiz-storage/sa_10_a.png",
            option2 = "https://sikarir.github.io/image-quiz-storage/sa_10_b.png",
            option3 = "https://sikarir.github.io/image-quiz-storage/sa_10_c.png",
            option4 = "https://sikarir.github.io/image-quiz-storage/sa_10_d.png",
            option5 = "https://sikarir.github.io/image-quiz-storage/sa_10_e.png"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpatialAptitudeBinding.inflate(layoutInflater)

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
                val intent = Intent(this, AbstractReasoningActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
            }
        }

        binding.btnPrevious.setOnClickListener {
            if (questionPointer > 0) {
                questionPointer--
                bindViews()
            } else {
                val intent = Intent(this, NumericalAptitudeActivity::class.java)
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
            binding.tvQuizPointer.text = getString(R.string.quiz_pointer).format(it.no)
            Glide.with(this).load(question.imageUrl).into(binding.ivQuizImage)
            binding.tvQuizQuestions.text = it.questionText
            adapter = SpatialAptitudeAnswerAdapter(question) {
                checkIfAnswerSelected()
            }
            binding.recyclerViewOptions.adapter = adapter
            binding.recyclerViewOptions.layoutManager = LinearLayoutManager(this)
        }
        checkIfAnswerSelected()
    }

    private fun checkIfAnswerSelected() {
        val question = questions[questionPointer]
        if (question.userAnswer == "") {
            enableNextButton(false)
        } else {
            enableNextButton(true)
        }
    }

    private fun enableNextButton(enable: Boolean) {
        binding.btnNext.isEnabled = enable
        binding.btnNext.alpha = if (enable) 1.0f else 0.5f
    }
}