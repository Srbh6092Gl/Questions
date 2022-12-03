package com.srbh.questions.api

import com.srbh.questions.model.Question
import com.srbh.questions.model.QuestionRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuestionService {

    @GET("/ques")
    fun getAllQuestions(): Call<List<Question>>

    @POST("/ques")
    fun addQues(@Body questionRequest: QuestionRequest): Call<Question>
}