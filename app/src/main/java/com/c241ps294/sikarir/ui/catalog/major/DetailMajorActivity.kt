package com.c241ps294.sikarir.ui.catalog.major

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.data.remote.response.ListCareerItem
import com.c241ps294.sikarir.data.remote.response.ListJurusanTerkaitItem
import com.c241ps294.sikarir.data.remote.response.ListMajorItem
import com.c241ps294.sikarir.databinding.ActivityDetailCareerBinding
import com.c241ps294.sikarir.databinding.ActivityDetailMajorBinding
import com.c241ps294.sikarir.ui.adapter.RelatedMajorsAdapter
import com.c241ps294.sikarir.ui.adapter.RelatedUniversitiesAdapter

class DetailMajorActivity : AppCompatActivity() {
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
