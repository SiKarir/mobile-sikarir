package com.c241ps294.sikarir.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.c241ps294.sikarir.data.remote.response.ListCareerItem
import com.c241ps294.sikarir.databinding.ItemCareerRecBinding
import com.c241ps294.sikarir.ui.catalog.career.DetailCareerActivity

class RecommendedCareerAdapter : RecyclerView.Adapter<RecommendedCareerAdapter.ViewHolder>() {

    private val recommendedCareers = mutableListOf<ListCareerItem>()

    fun submitList(list: List<ListCareerItem>) {
        recommendedCareers.clear()
        recommendedCareers.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCareerRecBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListCareerItem) {
            binding.tvCenterText.text = item.name

            binding.cardView.setOnClickListener{
                val intent = Intent(itemView.context, DetailCareerActivity::class.java)
                intent.putExtra(DetailCareerActivity.EXTRA_DATA, item)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCareerRecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recommendedCareers[position])
    }

    override fun getItemCount(): Int = recommendedCareers.size
}