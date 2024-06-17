package com.c241ps294.sikarir.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.data.local.quiz.AbstractReasoning
import com.c241ps294.sikarir.databinding.ItemAnswerImageBinding

class AbstractReasoningAnswerAdapter(private val question: AbstractReasoning, private val onAnswerSelected: () -> Unit) : RecyclerView.Adapter<AbstractReasoningAnswerAdapter.ListViewHolder>() {

    private var options: List<String> = listOf(question.option1, question.option2, question.option3, question.option4, question.option5)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemAnswerImageBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = options.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val option = options[position]
        Glide.with(holder.itemView).load(option).into(holder.binding.ivCenterImage)
        holder.itemView.setOnClickListener {
            question.userAnswer = position.toString()
            notifyDataSetChanged()
            onAnswerSelected()
        }

        if (question.userAnswer == position.toString()) {
            holder.itemView.setBackgroundResource(R.drawable.option_selected)
        }
        else {
            holder.itemView.setBackgroundResource(R.drawable.option_default)
        }
    }

    class ListViewHolder(var binding: ItemAnswerImageBinding) : RecyclerView.ViewHolder(binding.root)
}