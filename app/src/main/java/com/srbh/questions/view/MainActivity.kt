package com.srbh.questions.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.srbh.questions.R
import com.srbh.questions.adapter.QuestionAdapter
import com.srbh.questions.databinding.ActivityMainBinding
import com.srbh.questions.viewmodel.QuestionViewModel
import com.srbh.questions.viewmodel.QuestionViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var factory: QuestionViewModelFactory
    private lateinit var quesViewModel: QuestionViewModel
    private lateinit var quesAdapter: QuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        factory = QuestionViewModelFactory()
        quesViewModel = ViewModelProvider(this,factory).get(QuestionViewModel::class.java)
        quesViewModel.getAllQuestions()

        binding.quesVM = quesViewModel
        binding.lifecycleOwner = this
        binding.quesRv.layoutManager = LinearLayoutManager(this)

        quesAdapter = QuestionAdapter()
        binding.quesRv.adapter = quesAdapter

        displayQues()

        binding.add.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
        binding.refreshButton.setOnClickListener{
            displayQues()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume: reached")
        displayQues()
    }

    private fun displayQues() {
        quesViewModel.getAllQuestions()
        quesViewModel.quesList.observe(this,Observer {
            quesAdapter.setList(it)
            Log.i("MainActivity", "displayQues: ${it.size}")
            quesAdapter.notifyDataSetChanged()
        })
    }
}