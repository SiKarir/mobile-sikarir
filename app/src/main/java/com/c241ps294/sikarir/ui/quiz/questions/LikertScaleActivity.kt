package com.c241ps294.sikarir.ui.quiz.questions

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.data.local.quiz.LikertScale
import com.c241ps294.sikarir.databinding.ActivityLikertScaleBinding
import com.c241ps294.sikarir.ui.adapter.LikertScaleAnswerAdapter

class LikertScaleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLikertScaleBinding
    private lateinit var adapter: LikertScaleAnswerAdapter
    private var questionPointer = 0
    private val questions = listOf(
        LikertScale(1, "Saya seringkali menjadi mood maker dalam suatu kelompok", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
        LikertScale(2, "Saya merasa kurang peduli terhadap orang lain", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
        LikertScale(3, "Saya selalu penuh persiapan dalam segala kondisi", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(4, "Saya mudah terkena stres", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(5, "Saya memiliki banyak pengetahuan kosakata", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(6, "Saya tidak banyak bicara", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(7, "Saya memiliki ketertarikan pada orang banyak", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(8, "Saya sering meninggalkan barang-barang saya", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(9, "Saya merasa rileks hampir setiap saat", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(10, "Saya mengalami kesulitan dalam memahami ide-ide abstrak", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(11, "Saya merasa nyaman berada di sekitar orang lain", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(12, "Saya seringkali menghina orang lain", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(13, "Saya merupakan orang yang memperhatikan detail", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(14, "Saya seringkali khawatir mengenai banyak hal", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(15, "Saya memiliki imajinasi yang hidup", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(16, "Saya sering berada di belakang orang lain", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(17, "Saya mampu bersimpati dengan perasaan orang lain", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(18, "Saya merupakan orang yang kurang rapih", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(19, "Saya jarang merasa sedih", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(20, "Saya tidak tertarik pada ide-ide abstrak", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(21, "Saya pandai memulai percakapan", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(22, "Saya tidak tertarik dengan masalah orang lain", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(23, "Saya dapat menyelesaikan suatu pekerjaan dengan cepat", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(24, "Saya merupakan orang yang mudah terganggu", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(25, "Saya memiliki ide-ide yang cemerlang", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(26, "Saya tidak memiliki banyak hal yang ingin saya bicarakan", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(27, "Saya memiliki hati yang lembut", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(28, "Saya seringkali lupa mengembalikan barang pada tempatnya", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(29, "Saya merupakan orang yang mudah marah", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(30, "Saya tidak memiliki imajinasi yang baik", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(31, "Saya berbicara dengan banyak orang yang berbeda di suatu perkumpulan ramai", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(32, "Saya tidak terlalu tertarik pada orang lain", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(33, "Saya menyukai ketertiban", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(34, "Saya banyak mengalami perubahan mood", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(35, "Saya cepat memahami sesuatu", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(36, "Saya tidak suka menarik perhatian pada diri sendiri", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(37, "Saya meluangkan waktu untuk orang lain", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(38, "Saya melalaikan tugas-tugas saya", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(39, "Saya sering mengalami perubahan mood", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(40, "Saya banyak menggunakan kata-kata yang sulit", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(41, "Saya tidak keberatan menjadi pusat perhatian", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(42, "Saya mampu merasakan perasaan orang lain", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(43, "Saya tertib mengikuti jadwal", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(44, "Saya mudah tersinggung", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(45, "Saya membutuhkan waktu untuk merenungkan sesuatu", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(46, "Saya tidak banyak bicara di sekitar orang asing", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(47, "Saya membuat orang lain merasa nyaman", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(48, "Saya teliti dalam pekerjaan saya", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(49, "Saya sering merasa sedih", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju"),
//        LikertScale(50, "Saya penuh dengan ide", "Sangat Tidak Setuju", "Tidak Setuju", "Netral", "Setuju", "Sangat Setuju")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLikertScaleBinding.inflate(layoutInflater)
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
                val intent = Intent(this, NumericalAptitudeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
            }
        }

        binding.btnPrevious.setOnClickListener {
            if (questionPointer > 0) {
                questionPointer--
                bindViews()
            }
        }
    }

    private fun bindViews() {
        binding.btnNext.visibility = View.GONE
        binding.btnPrevious.visibility = View.GONE

        if (questionPointer == 0) {
            binding.btnNext.visibility = View.VISIBLE
            binding.btnPrevious.visibility = View.GONE
        } else {
            binding.btnNext.visibility = View.VISIBLE
            binding.btnPrevious.visibility = View.VISIBLE
        }

        val question = questions[questionPointer]
        question.let {
            binding.tvQuizPointer.text = getString(R.string.quiz_pointer).format(it.no)
            binding.tvQuizQuestions.text = it.questionText
            adapter = LikertScaleAnswerAdapter(question) {
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