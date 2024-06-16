package com.c241ps294.sikarir.ui.quiz.questions

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.c241ps294.sikarir.data.local.quiz.NumericalAptitude
import com.c241ps294.sikarir.databinding.ActivityNumericalAptitudeBinding
import com.c241ps294.sikarir.ui.adapter.NumericalAptitudeAnswerAdapter
import com.c241ps294.sikarir.ui.quiz.result.QuizResultActivity

class NumericalAptitudeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNumericalAptitudeBinding
    private lateinit var adapter: NumericalAptitudeAnswerAdapter
    private var questionPointer = 0
    val questions = listOf(
        NumericalAptitude(
            no = 51,
            questionText = "Dengan 4 liter bensin sebuah mobil dapat menempuh jarak 32 km. Jika jarak yang akan ditempuh adalah 56 km, berapa liter bensin yang diperlukan?",
            option1 = "5 liter",
            option2 = "6 liter",
            option3 = "7 liter",
            option4 = "8 liter",
            option5 = "9 liter"
        ),
        NumericalAptitude(
            no = 52,
            questionText = "Andi berjalan ke arah Utara dengan menempuh jarak 2/5 km dalam waktu 5 menit, berapa jarak perjalanan Andi dalam waktu 1 jam ke depan?",
            option1 = "4,5 km",
            option2 = "4,3 km",
            option3 = "5 km",
            option4 = "4,8 km",
            option5 = "5,3 km"
        ),
        NumericalAptitude(
            no = 53,
            questionText = "Terdapat sebuah kebun bunga berbentuk persegi panjang dengan panjang 16 m dan lebar 12 m. Sekeliling kebun bunga dibangun jalan yang lebarnya 3 m. Berapa luas jalan tersebut?",
            option1 = "93 m2",
            option2 = "168 m2",
            option3 = "204 m2",
            option4 = "178 m2",
            option5 = "192 m2"
        ),
        NumericalAptitude(
            no = 54,
            questionText = "Sebuah wadah berbentuk silinder diisi air 1/3-nya, lalu wadah tersebut ditambah air sebanyak 3 liter sehingga isinya kini menjadi 1/2-nya. Berapa kapasitas wadah tersebut?",
            option1 = "25 liter",
            option2 = "18 liter",
            option3 = "20 liter",
            option4 = "22 liter",
            option5 = "24 liter"
        ),
        NumericalAptitude(
            no = 55,
            questionText = "Jika x rupiah dibagi rata kepada n orang, setiap orang akan memperoleh bagian Rp180.000,00. Jika dua orang lagi bergabung pada kelompok itu dengan jumlah uang yang sama, setiap orang akan memperoleh Rp150.000,00. Berapa rupiah uang itu?",
            option1 = "Rp1.700.000,00",
            option2 = "Rp1.750.000,00",
            option3 = "Rp1.800.000,00",
            option4 = "Rp1.850.000,00",
            option5 = "Rp1.900.000,00"
        ),
        NumericalAptitude(
            no = 56,
            questionText = "Jika x = 1/60 dan y = 60%, maka",
            option1 = "x > y",
            option2 = "x < y",
            option3 = "x = y",
            option4 = "x dan y tidak dapat ditentukan",
            option5 = "x2 > y"
        ),
        NumericalAptitude(
            no = 57,
            questionText = "Jika a – b = 50 dan a/b = 2/4, berapakah b – a?",
            option1 = "-150",
            option2 = "-320",
            option3 = "-450",
            option4 = "-125",
            option5 = "-50"
        ),
        NumericalAptitude(
            no = 58,
            questionText = "11 x 9 : 99 – 0 = …",
            option1 = "0",
            option2 = "1",
            option3 = "2",
            option4 = "3",
            option5 = "4"
        ),
        NumericalAptitude(
            no = 59,
            questionText = "9 x 2 x 7 – 65 – 40 = …",
            option1 = "13.230",
            option2 = "-13.230",
            option3 = "21",
            option4 = "231",
            option5 = "101"
        ),
        NumericalAptitude(
            no = 60,
            questionText = "455 x 45 : 455 – 3 = …",
            option1 = "-3",
            option2 = "42",
            option3 = "45",
            option4 = "55",
            option5 = "65"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumericalAptitudeBinding.inflate(layoutInflater)

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
                val intent = Intent(this, SpatialAptitudeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
            }
        }

        binding.btnPrevious.setOnClickListener {
            if (questionPointer > 0) {
                questionPointer--
                bindViews()
            } else {
                val intent = Intent(this, LikertScaleActivity::class.java)
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
            binding.tvQuizQuestions.text = it.questionText
            adapter = NumericalAptitudeAnswerAdapter(question)
            binding.recyclerViewOptions.adapter = adapter
            binding.recyclerViewOptions.layoutManager = LinearLayoutManager(this)
        }
    }
}