package com.srbh.questions.viewmodel

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.srbh.questions.api.QuestionService
import com.srbh.questions.api.RetrofitHelper
import com.srbh.questions.model.Question
import com.srbh.questions.model.QuestionRequest
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionViewModel:ViewModel(),Observable {

    var quesList = MutableLiveData<List<Question>>()
    lateinit var questionService: QuestionService

    @Bindable
    val question = MutableLiveData<String>()

    @Bindable
    val answer = MutableLiveData<String>()

    init{
        questionService = RetrofitHelper.getInstance().create(QuestionService::class.java)
    }

    fun getAllQuestions() {
        GlobalScope.launch {
            questionService.getAllQuestions().enqueue(object : Callback<List<Question>> {
                override fun onResponse(
                    call: Call<List<Question>>,
                    response: Response<List<Question>>
                ) {
                    quesList.value = response.body()!!
                    Log.i("GET Request", "Success")
                    Log.i("onResponse", quesList.toString())
                }
                override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                    Log.i("GET Request", "Failed")
                    call.cancel()
                }
            })
        }
    }

    fun add(questionRequest: QuestionRequest) = questionService.addQues(questionRequest).enqueue(object :Callback<Question>{
        override fun onResponse(call: Call<Question>, response: Response<Question>) {
            Log.i("POST Request", "Success")
        }

        override fun onFailure(call: Call<Question>, t: Throwable) {
            Log.i("POST Request", "Failed")
            call.cancel()
        }

    })


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}