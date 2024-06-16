package com.c241ps294.sikarir.ui.catalog.major

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.data.remote.response.ListMajorItem
import com.c241ps294.sikarir.databinding.ActivityDetailMajorBinding
import com.c241ps294.sikarir.ui.adapter.RelatedUniversitiesAdapter
import com.c241ps294.sikarir.ui.home.MainActivity
import com.c241ps294.sikarir.ui.quiz.starter.QuizStarterActivity
import com.c241ps294.sikarir.ui.settings.SettingsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailMajorActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var binding: ActivityDetailMajorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMajorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val major = intent.getParcelableExtra<ListMajorItem>(EXTRA_DATA)
        major?.let {
            binding.detailMajorTitle.text = it.name
            binding.detailMajorDescription.text = it.description
            Glide.with(this).load(it.photoUrl).into(binding.ivDetailMajorImage)
            setupUniversityRecyclerView(it.listUniversitasTerkait?.split(",")?.map { it.trim() })
        }

        bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.selectedItemId = R.id.catalog_page
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_page -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent, ActivityOptions.makeCustomAnimation(this, 0, 0).toBundle())
                    true
                }
                R.id.catalog_page -> {
                    true
                }
                R.id.quiz_page -> {
                    val intent = Intent(this, QuizStarterActivity::class.java)
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
    }

    private fun setupUniversityRecyclerView(universities: List<String>?) {
        universities?.let {
            val adapter = RelatedUniversitiesAdapter(it)
            binding.rvUniversity.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.rvUniversity.adapter = adapter
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
