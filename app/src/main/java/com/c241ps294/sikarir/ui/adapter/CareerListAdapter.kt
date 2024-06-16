package com.c241ps294.sikarir.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.c241ps294.sikarir.data.remote.response.ListCareerItem
import com.c241ps294.sikarir.databinding.ItemCareerCatalogBinding
import com.c241ps294.sikarir.ui.catalog.career.DetailCareerActivity

class CareerListAdapter : PagingDataAdapter<ListCareerItem, CareerListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCareerCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class MyViewHolder(private val binding: ItemCareerCatalogBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(career: ListCareerItem){
            binding.tvCenterText.text = career.name

            binding.cardView.setOnClickListener{
                val intent = Intent(itemView.context, DetailCareerActivity::class.java)
                intent.putExtra(DetailCareerActivity.EXTRA_DATA, career)
                itemView.context.startActivity(intent)
            }
    }
}
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListCareerItem>() {
            override fun areItemsTheSame(oldItem: ListCareerItem, newItem: ListCareerItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ListCareerItem, newItem: ListCareerItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}