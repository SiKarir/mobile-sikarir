package com.c241ps294.sikarir.ui.catalog.career

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.data.remote.response.ListCareerItem
import com.c241ps294.sikarir.data.remote.response.ListMajorItem
import com.c241ps294.sikarir.databinding.ActivityDetailCareerBinding
import com.c241ps294.sikarir.ui.adapter.RelatedMajorsAdapter
import com.c241ps294.sikarir.ui.home.MainActivity
import com.c241ps294.sikarir.ui.quiz.starter.QuizStarterActivity
import com.c241ps294.sikarir.ui.settings.SettingsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailCareerActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var binding: ActivityDetailCareerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCareerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val career = intent.getParcelableExtra<ListCareerItem>(EXTRA_DATA)
        career?.let {
            binding.detailCareerTitle.text = it.name
            binding.detailCareerDescription.text = it.description
            Log.d("DetailCareerActivity", "Photo URL: ${it.photoUrl}")
            Glide.with(this).load(it.photoUrl).into(binding.ivDetailCareerImage)
            setupRelatedMajorsRecyclerView(it.listJurusanTerkait)
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

    private fun setupRelatedMajorsRecyclerView(majors: List<ListMajorItem?>?) {
        majors?.filterNotNull()?.let {
            val adapter = RelatedMajorsAdapter(it)
            binding.rvMajor.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.rvMajor.adapter = adapter
        }
    }
    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
