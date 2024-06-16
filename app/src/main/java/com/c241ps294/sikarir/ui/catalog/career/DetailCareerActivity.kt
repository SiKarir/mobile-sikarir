package com.c241ps294.sikarir.ui.catalog.career

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.c241ps294.sikarir.data.remote.response.ListCareerItem
import com.c241ps294.sikarir.data.remote.response.ListJurusanTerkaitItem
import com.c241ps294.sikarir.databinding.ActivityDetailCareerBinding
import com.c241ps294.sikarir.ui.adapter.RelatedMajorsAdapter

class DetailCareerActivity : AppCompatActivity() {
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
    }

    private fun setupRelatedMajorsRecyclerView(majors: List<ListJurusanTerkaitItem?>?) {
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
