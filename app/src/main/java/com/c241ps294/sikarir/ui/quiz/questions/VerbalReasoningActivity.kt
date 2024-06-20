package com.c241ps294.sikarir.ui.quiz.questions

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.data.local.quiz.VerbalReasoning
import com.c241ps294.sikarir.databinding.ActivityVerbalReasoningBinding
import com.c241ps294.sikarir.ui.adapter.VerbalReasoningAnswerAdapter

class VerbalReasoningActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVerbalReasoningBinding
    private lateinit var adapter: VerbalReasoningAnswerAdapter
    private var questionPointer = 0
    private val questions = listOf(
        VerbalReasoning(
            no = 81,
            questionText = "Lokasi kerja Indra berada di Jakarta.",
            info1 = "- Indra bekerja sebagai operator di sebuah pabrik minuman serbuk:",
            info2 = "- Indra harus bekerja pada hari Senin-Sabtu.",
            info3 = "- Pekerjaan Indra dibagi menjadi tiga shift, yaitu shift pagi, shift middle, dan shift malam.",
            info4 = "- Setiap bulan HRD akan membagi jadwal kerja para karyawannya.",
            info5 = "- Jika Indra ingin request cuti di hari tertentu, maka ia perlu infokan ke HRD satu bulan sebelum rencana cuti tersebut.",
            info6 = "- Jadwal kerjanya selama delapan jam kerja dengan pilihan jam masuk shift pagi pada jam 07.00, shift middle jam 12.00, dan shift malam jam 21.00.",
            info7 = "- Indra perlu berangkat satu jam sebelum jam masuk kerja menggunakan motor.",
            info8 = "- Indra bisa saja masuk pada tanggal merah, tetapi hari Minggu ia pasti libur.",
            option1 = "Tidak dapat disimpulkan",
            option2 = "Simpulan adalah benar",
            option3 = "Simpulan adalah salah"
        ),
        VerbalReasoning(
            no = 82,
            questionText = "Indra dapat request cuti selama tiga hari berturut-turut.",
            info1 = "- Indra bekerja sebagai operator di sebuah pabrik minuman serbuk:",
            info2 = "- Indra harus bekerja pada hari Senin-Sabtu.",
            info3 = "- Pekerjaan Indra dibagi menjadi tiga shift, yaitu shift pagi, shift middle, dan shift malam.",
            info4 = "- Setiap bulan HRD akan membagi jadwal kerja para karyawannya.",
            info5 = "- Jika Indra ingin request cuti di hari tertentu, maka ia perlu infokan ke HRD satu bulan sebelum rencana cuti tersebut.",
            info6 = "- Jadwal kerjanya selama delapan jam kerja dengan pilihan jam masuk shift pagi pada jam 07.00, shift middle jam 12.00, dan shift malam jam 21.00.",
            info7 = "- Indra perlu berangkat satu jam sebelum jam masuk kerja menggunakan motor.",
            info8 = "- Indra bisa saja masuk pada tanggal merah, tetapi hari Minggu ia pasti libur.",
            option1 = "Tidak dapat disimpulkan",
            option2 = "Simpulan adalah benar",
            option3 = "Simpulan adalah salah"
        ),
        VerbalReasoning(
            no = 83,
            questionText = "Jika Indra dapat shift middle maka ia harus berangkat kerja pukul 11.00.",
            info1 = "- Indra bekerja sebagai operator di sebuah pabrik minuman serbuk:",
            info2 = "- Indra harus bekerja pada hari Senin-Sabtu.",
            info3 = "- Pekerjaan Indra dibagi menjadi tiga shift, yaitu shift pagi, shift middle, dan shift malam.",
            info4 = "- Setiap bulan HRD akan membagi jadwal kerja para karyawannya.",
            info5 = "- Jika Indra ingin request cuti di hari tertentu, maka ia perlu infokan ke HRD satu bulan sebelum rencana cuti tersebut.",
            info6 = "- Jadwal kerjanya selama delapan jam kerja dengan pilihan jam masuk shift pagi pada jam 07.00, shift middle jam 12.00, dan shift malam jam 21.00.",
            info7 = "- Indra perlu berangkat satu jam sebelum jam masuk kerja menggunakan motor.",
            info8 = "- Indra bisa saja masuk pada tanggal merah, tetapi hari Minggu ia pasti libur.",
            option1 = "Tidak dapat disimpulkan",
            option2 = "Simpulan adalah benar",
            option3 = "Simpulan adalah salah"
        ),
        VerbalReasoning(
            no = 84,
            questionText = "Jika Indra dapat shift malam, maka ia selesai kerja pada jam 07.00.",
            info1 = "- Indra bekerja sebagai operator di sebuah pabrik minuman serbuk:",
            info2 = "- Indra harus bekerja pada hari Senin-Sabtu.",
            info3 = "- Pekerjaan Indra dibagi menjadi tiga shift, yaitu shift pagi, shift middle, dan shift malam.",
            info4 = "- Setiap bulan HRD akan membagi jadwal kerja para karyawannya.",
            info5 = "- Jika Indra ingin request cuti di hari tertentu, maka ia perlu infokan ke HRD satu bulan sebelum rencana cuti tersebut.",
            info6 = "- Jadwal kerjanya selama delapan jam kerja dengan pilihan jam masuk shift pagi pada jam 07.00, shift middle jam 12.00, dan shift malam jam 21.00.",
            info7 = "- Indra perlu berangkat satu jam sebelum jam masuk kerja menggunakan motor.",
            info8 = "- Indra bisa saja masuk pada tanggal merah, tetapi hari Minggu ia pasti libur.",
            option1 = "Tidak dapat disimpulkan",
            option2 = "Simpulan adalah benar",
            option3 = "Simpulan adalah salah"
        ),
        VerbalReasoning(
            no = 85,
            questionText = "Indra sudah bekerja di perusahaan tersebut selama dua tahun.",
            info1 = "- Indra bekerja sebagai operator di sebuah pabrik minuman serbuk:",
            info2 = "- Indra harus bekerja pada hari Senin-Sabtu.",
            info3 = "- Pekerjaan Indra dibagi menjadi tiga shift, yaitu shift pagi, shift middle, dan shift malam.",
            info4 = "- Setiap bulan HRD akan membagi jadwal kerja para karyawannya.",
            info5 = "- Jika Indra ingin request cuti di hari tertentu, maka ia perlu infokan ke HRD satu bulan sebelum rencana cuti tersebut.",
            info6 = "- Jadwal kerjanya selama delapan jam kerja dengan pilihan jam masuk shift pagi pada jam 07.00, shift middle jam 12.00, dan shift malam jam 21.00.",
            info7 = "- Indra perlu berangkat satu jam sebelum jam masuk kerja menggunakan motor.",
            info8 = "- Indra bisa saja masuk pada tanggal merah, tetapi hari Minggu ia pasti libur.",
            option1 = "Tidak dapat disimpulkan",
            option2 = "Simpulan adalah benar",
            option3 = "Simpulan adalah salah"
        ),
        VerbalReasoning(
            no = 86,
            questionText = "Rio harus membuang sampah pada hari Rabu dan Sabtu.",
            info1 = "- Rio bertugas untuk membuang sampah dan membuang air dari AC.",
            info2 = "- Indah memilih untuk mengerjakan tugas cuci piring, menyapu dan mengepel.",
            info3 = "- Bapak Ari sebagai ayah yang bertugas untuk angkat galon, membersihkan kamar mandi dan mencuci motor.",
            info4 = "- Ibu bertugas untuk memasak dan mencuci baju.",
            info5 = "- Vira merasa menyetrika baju sudah cukup membuatnya lelah.",
            info6 = "- Sampah perlu dibuang setiap hari Rabu dan Sabtu.",
            info7 = "",
            info8 = "",
            option1 = "Tidak dapat disimpulkan",
            option2 = "Simpulan adalah benar",
            option3 = "Simpulan adalah salah"
        ),
        VerbalReasoning(
            no = 87,
            questionText = "Vira yang memilih tugas untuk memasak.",
            info1 = "- Rio bertugas untuk membuang sampah dan membuang air dari AC.",
            info2 = "- Indah memilih untuk mengerjakan tugas cuci piring, menyapu dan mengepel.",
            info3 = "- Bapak Ari sebagai ayah yang bertugas untuk angkat galon, membersihkan kamar mandi dan mencuci motor.",
            info4 = "- Ibu bertugas untuk memasak dan mencuci baju.",
            info5 = "- Vira merasa menyetrika baju sudah cukup membuatnya lelah.",
            info6 = "- Sampah perlu dibuang setiap hari Rabu dan Sabtu.",
            info7 = "",
            info8 = "",
            option1 = "Tidak dapat disimpulkan",
            option2 = "Simpulan adalah benar",
            option3 = "Simpulan adalah salah"
        ),
        VerbalReasoning(
            no = 88,
            questionText = "Bapak bertugas membersihkan kamar mandi setiap hari Minggu.",
            info1 = "- Rio bertugas untuk membuang sampah dan membuang air dari AC.",
            info2 = "- Indah memilih untuk mengerjakan tugas cuci piring, menyapu dan mengepel.",
            info3 = "- Bapak Ari sebagai ayah yang bertugas untuk angkat galon, membersihkan kamar mandi dan mencuci motor.",
            info4 = "- Ibu bertugas untuk memasak dan mencuci baju.",
            info5 = "- Vira merasa menyetrika baju sudah cukup membuatnya lelah.",
            info6 = "- Sampah perlu dibuang setiap hari Rabu dan Sabtu.",
            info7 = "",
            info8 = "",
            option1 = "Tidak dapat disimpulkan",
            option2 = "Simpulan adalah benar",
            option3 = "Simpulan adalah salah"
        ),
        VerbalReasoning(
            no = 89,
            questionText = "Indah yang bertugas untuk menyapu.",
            info1 = "- Rio bertugas untuk membuang sampah dan membuang air dari AC.",
            info2 = "- Indah memilih untuk mengerjakan tugas cuci piring, menyapu dan mengepel.",
            info3 = "- Bapak Ari sebagai ayah yang bertugas untuk angkat galon, membersihkan kamar mandi dan mencuci motor.",
            info4 = "- Ibu bertugas untuk memasak dan mencuci baju.",
            info5 = "- Vira merasa menyetrika baju sudah cukup membuatnya lelah.",
            info6 = "- Sampah perlu dibuang setiap hari Rabu dan Sabtu.",
            info7 = "",
            info8 = "",
            option1 = "Tidak dapat disimpulkan",
            option2 = "Simpulan adalah benar",
            option3 = "Simpulan adalah salah"
        ),
        VerbalReasoning(
            no = 90,
            questionText = "Pakaian disetrika setiap dua kali seminggu.",
            info1 = "- Rio bertugas untuk membuang sampah dan membuang air dari AC.",
            info2 = "- Indah memilih untuk mengerjakan tugas cuci piring, menyapu dan mengepel.",
            info3 = "- Bapak Ari sebagai ayah yang bertugas untuk angkat galon, membersihkan kamar mandi dan mencuci motor.",
            info4 = "- Ibu bertugas untuk memasak dan mencuci baju.",
            info5 = "- Vira merasa menyetrika baju sudah cukup membuatnya lelah.",
            info6 = "- Sampah perlu dibuang setiap hari Rabu dan Sabtu.",
            info7 = "",
            info8 = "",
            option1 = "Tidak dapat disimpulkan",
            option2 = "Simpulan adalah benar",
            option3 = "Simpulan adalah salah"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerbalReasoningBinding.inflate(layoutInflater)

        setContentView(binding.root)

        bindViews()
        setUpEventListener()

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (questionPointer == 0) {
                    val intent = Intent(this@VerbalReasoningActivity, AbstractReasoningActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                    startActivity(intent, ActivityOptions.makeCustomAnimation(this@VerbalReasoningActivity, 0, 0).toBundle())
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
                val intent = Intent(this, PerceptualAptitudeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
            }
        }

        binding.btnPrevious.setOnClickListener {
            if (questionPointer > 0) {
                questionPointer--
                bindViews()
            } else {
                val intent = Intent(this, AbstractReasoningActivity::class.java)
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
            binding.info1.text = it.info1
            binding.info2.text = it.info2
            binding.info3.text = it.info3
            binding.info4.text = it.info4
            binding.info5.text = it.info5
            binding.info6.text = it.info6
            binding.info7.text = it.info7
            binding.info8.text = it.info8
            binding.tvQuizPointer.text = getString(R.string.quiz_pointer).format(it.no)
            binding.tvQuizQuestions.text = it.questionText
            adapter = VerbalReasoningAnswerAdapter(question){
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