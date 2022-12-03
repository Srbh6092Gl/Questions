package com.srbh.questions.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.srbh.questions.R
import com.srbh.questions.databinding.ItemQuesBinding
import com.srbh.questions.model.Question

class QuestionAdapter: RecyclerView.Adapter<QuestionViewHolder>() {

    private val quesList = ArrayList<Question>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemQuesBinding= DataBindingUtil.inflate(layoutInflater, R.layout.item_ques,parent,false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(quesList[position])
    }

    override fun getItemCount() = quesList.size

    fun setList(list: List<Question>?) {
        quesList.clear()
        quesList.addAll(list!!)
    }
}
class QuestionViewHolder(val binding: ItemQuesBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(question: Question){
        binding.itemQuestion.text = question.ques
        binding.itemAnswer.text = question.ans
    }

}