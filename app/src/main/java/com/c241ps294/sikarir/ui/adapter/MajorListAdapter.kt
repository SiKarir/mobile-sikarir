package com.c241ps294.sikarir.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.c241ps294.sikarir.data.remote.response.ListCareerItem
import com.c241ps294.sikarir.data.remote.response.ListMajorItem
import com.c241ps294.sikarir.databinding.ItemMajorCatalogBinding
import com.c241ps294.sikarir.ui.catalog.major.DetailMajorActivity

class MajorListAdapter : PagingDataAdapter<ListMajorItem, MajorListAdapter.MyViewHolder>(DIFF_CALLBACK) {
    private var nonPaginatedData: List<ListMajorItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemMajorCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = if (nonPaginatedData != null) {
            nonPaginatedData!![position]
        } else {
            getItem(position)
        }
        item?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return nonPaginatedData?.size ?: super.getItemCount()
    }

    fun submitNonPaginatedList(data: List<ListMajorItem>) {
        nonPaginatedData = data
        notifyDataSetChanged()
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