package com.c241ps294.sikarir.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.c241ps294.sikarir.data.remote.response.ListMajorItem
import com.c241ps294.sikarir.databinding.ItemMajorBinding
import com.c241ps294.sikarir.ui.catalog.major.DetailMajorActivity

class RelatedMajorsAdapter(private val majors: List<ListMajorItem>) : RecyclerView.Adapter<RelatedMajorsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemMajorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(majors[position])
    }

    override fun getItemCount(): Int = majors.size

    class MyViewHolder(private val binding: ItemMajorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(major: ListMajorItem) {
            binding.tvCenterText.text = major.name

            binding.root.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, DetailMajorActivity::class.java)
                intent.putExtra(DetailMajorActivity.EXTRA_DATA, major)
                context.startActivity(intent)
            }
        }
    }
}