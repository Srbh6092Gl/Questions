package com.srbh.questions.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.srbh.questions.R
import com.srbh.questions.databinding.ActivityAddBinding
import com.srbh.questions.model.QuestionRequest
import com.srbh.questions.viewmodel.QuestionViewModel
import com.srbh.questions.viewmodel.QuestionViewModelFactory

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var quesViewModel: QuestionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add)
        val factory = QuestionViewModelFactory()
        quesViewModel = ViewModelProvider(this,factory).get(QuestionViewModel::class.java)
        binding.quesVM = quesViewModel
        binding.lifecycleOwner = this

        binding.addNoteButton.setOnClickListener{
            val ques = quesViewModel.question.value
            val ans = quesViewModel.answer.value
            if(ques != "" && ans != "" && ques != null && ans != null){
                quesViewModel.add(
                    QuestionRequest(ques = ques, ans = ans)
                )
                goBackToMainActivity()
            }
            else
                Toast.makeText(this, "Field Empty", Toast.LENGTH_SHORT).show()
        }
        binding.backButton.setOnClickListener{
            goBackToMainActivity()
        }
    }

    private fun goBackToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
}