package com.c241ps294.sikarir.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.c241ps294.sikarir.data.remote.response.ListMajorItem
import com.c241ps294.sikarir.databinding.ItemMajorCatalogBinding
import com.c241ps294.sikarir.ui.catalog.major.DetailMajorActivity

class MajorListAdapter : PagingDataAdapter<ListMajorItem, MajorListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemMajorCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class MyViewHolder(private val binding: ItemMajorCatalogBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(major: ListMajorItem){
            binding.tvCenterText.text = major.name

            binding.cardView.setOnClickListener{
                val intent = Intent(itemView.context, DetailMajorActivity::class.java)
                intent.putExtra(DetailMajorActivity.EXTRA_DATA, major)
                itemView.context.startActivity(intent)
            }
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListMajorItem>() {
            override fun areItemsTheSame(oldItem: ListMajorItem, newItem: ListMajorItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ListMajorItem, newItem: ListMajorItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}