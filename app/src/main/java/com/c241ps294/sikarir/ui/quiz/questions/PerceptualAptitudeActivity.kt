package com.c241ps294.sikarir.ui.quiz.questions

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.data.local.quiz.PerceptualAptitude
import com.c241ps294.sikarir.data.local.storage.AnswerStorage
import com.c241ps294.sikarir.data.preference.user.User
import com.c241ps294.sikarir.databinding.ActivityPerceptualAptitudeBinding
import com.c241ps294.sikarir.ui.adapter.PerceptualAptitudeAnswerAdapter
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModel
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModelFactory
import com.c241ps294.sikarir.ui.quiz.result.QuizResultActivity
import com.c241ps294.sikarir.ui.quiz.viewmodel.QuizViewModel
import com.c241ps294.sikarir.ui.quiz.viewmodel.QuizViewModelFactory

class PerceptualAptitudeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerceptualAptitudeBinding
    private lateinit var adapter: PerceptualAptitudeAnswerAdapter
    private var questionPointer = 0
    private val questions = listOf(
        PerceptualAptitude(
            no = 91,
            questionText = "Pilihlah isi titik-titik yang paling sesuai! 1. 1, 5, 11, 19, 29, …, 55, …",
            option1 = "39 dan 69",
            option2 = "41 dan 71",
            option3 = "35 dan 65",
            option4 = "39 dan 65",
            option5 = "40 dan 71"
        ),
        PerceptualAptitude(
            no = 92,
            questionText = "Pilihlah isi titik-titik yang paling sesuai! 2. 6, 8, 11, 15, 20, …, 33",
            option1 = "25",
            option2 = "27",
            option3 = "28",
            option4 = "26",
            option5 = "32"
        ),
        PerceptualAptitude(
            no = 93,
            questionText = "Pilihlah isi titik-titik yang paling sesuai! 3. 4, 18, 42, 56, …, 96",
            option1 = "84",
            option2 = "76",
            option3 = "82",
            option4 = "92",
            option5 = "78"
        ),
        PerceptualAptitude(
            no = 94,
            questionText = "Pilihlah isi titik-titik yang paling sesuai! 4. 78, 76, 73, …, 64, 58",
            option1 = "66",
            option2 = "69",
            option3 = "65",
            option4 = "56",
            option5 = "60"
        ),
        PerceptualAptitude(
            no = 95,
            questionText = "Pilihlah isi titik-titik yang paling sesuai! 5. 1, 8, 9, 16, 17, …, 25",
            option1 = "24",
            option2 = "28",
            option3 = "34",
            option4 = "36",
            option5 = "26"
        ),
        PerceptualAptitude(
            no = 96,
            questionText = "Cermati susunan di bawah ini, berilah tanda “S” jika kedua susunan merupakan susunan yang sama, dan tanda “TS” jika kedua susunannya tidak sama!\n GIRX --- GRIX",
            option1 = "S",
            option2 = "TS",
            option3 = null,
            option4 = null,
            option5 = null
        ),
        PerceptualAptitude(
            no = 97,
            questionText = "Cermati susunan di bawah ini, berilah tanda “S” jika kedua susunan merupakan susunan yang sama, dan tanda “TS” jika kedua susunannya tidak sama!\n" +
                    "SERI --- SARI",
            option1 = "S",
            option2 = "TS",
            option3 = null,
            option4 = null,
            option5 = null
        ),
        PerceptualAptitude(
            no = 98,
            questionText = "Cermati susunan di bawah ini, berilah tanda “S” jika kedua susunan merupakan susunan yang sama, dan tanda “TS” jika kedua susunannya tidak sama!\n" +
                    " MLTHSTS --- MLTHTST",
            option1 = "S",
            option2 = "TS",
            option3 = null,
            option4 = null,
            option5 = null
        ),
        PerceptualAptitude(
            no = 99,
            questionText = "Cermati susunan di bawah ini, berilah tanda “S” jika kedua susunan merupakan susunan yang sama, dan tanda “TS” jika kedua susunannya tidak sama!\n" +
                    "  VIFTTWH --- VIFTTWH",
            option1 = "S",
            option2 = "TS",
            option3 = null,
            option4 = null,
            option5 = null
        ),
        PerceptualAptitude(
            no = 100,
            questionText = "Cermati susunan di bawah ini, berilah tanda “S” jika kedua susunan merupakan susunan yang sama, dan tanda “TS” jika kedua susunannya tidak sama!\n" +
                    "  SPURSV --- SPURSV",
            option1 = "S",
            option2 = "TS",
            option3 = null,
            option4 = null,
            option5 = null
        )
    )

    private val authenticationViewModel by viewModels<AuthenticationViewModel> {
        AuthenticationViewModelFactory.getInstance(context = this)
    }

    private val quizViewModel by viewModels<QuizViewModel> {
        QuizViewModelFactory.getInstance()
    }

    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerceptualAptitudeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authenticationViewModel.getSession().observe(this) {
            user = it
        }

        quizViewModel.quizResponse.observe(this) { response ->
            if (!response.error) {
                AnswerStorage.clearAnswers()

                val intent = Intent(this, QuizResultActivity::class.java)
                startActivity(intent)

                finishAffinity()

                val updatedUser = user.copy(isTakenQuiz = true)
                authenticationViewModel.saveSession(updatedUser)
            }
        }

        quizViewModel.errorMessage.observe(this) {
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        quizViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        bindViews()
        setUpEventListener()

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (questionPointer == 0) {
                    val intent = Intent(this@PerceptualAptitudeActivity, VerbalReasoningActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                    startActivity(intent, ActivityOptions.makeCustomAnimation(this@PerceptualAptitudeActivity, 0, 0).toBundle())
                } else {
                    questionPointer--
                    bindViews()
                }
            }
        })
    }

    private fun setUpEventListener() {
        binding.btnNext.setOnClickListener {
            if (questionPointer < questions.size - 1) {
                questionPointer++
                bindViews()
            } else {
                quizViewModel.postQuiz(user.token)
            }
        }

        binding.btnPrevious.setOnClickListener {
            if (questionPointer > 0) {
                questionPointer--
                bindViews()
            } else {
                val intent = Intent(this, VerbalReasoningActivity::class.java)
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
            binding.tvQuizQuestions.text = it.questionText
            adapter = PerceptualAptitudeAnswerAdapter(question){
                checkIfAnswerSelected()
            }
            binding.recyclerViewOptions.adapter = adapter
            binding.recyclerViewOptions.layoutManager = LinearLayoutManager(this)
        }

        if (questionPointer == questions.size - 1) {
            binding.btnNext.text = "Submit"
        } else {
            binding.btnNext.text = "Selanjutnya"
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

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}