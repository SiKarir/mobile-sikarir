package com.c241ps294.sikarir.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.c241ps294.sikarir.data.remote.response.QuizItem
import com.c241ps294.sikarir.databinding.ItemQuizHistoryBinding
import java.text.SimpleDateFormat
import java.util.Locale

class QuizHistoryAdapter : ListAdapter<QuizItem, QuizHistoryAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemQuizHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val quiz = getItem(position)
        holder.bind(quiz, position + 1)
    }

    class MyViewHolder(val binding: ItemQuizHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(quiz: QuizItem, position: Int){
            Log.d("QUIZ", quiz.toString())
            binding.tvTitleQuiz.text = "Quiz $position"

            val originalFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val targetFormat = SimpleDateFormat("d MMMM yyyy", Locale("id", "ID"))  // Indonesia locale

            val dateString = quiz.date
            val date = originalFormat.parse(dateString)
            val formattedDate = date?.let { targetFormat.format(it) }

            binding.textDateQuiz.text = formattedDate
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<QuizItem>() {
            override fun areItemsTheSame(oldItem: QuizItem, newItem: QuizItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: QuizItem, newItem: QuizItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}