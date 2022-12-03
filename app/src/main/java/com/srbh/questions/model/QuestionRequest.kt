package com.srbh.questions.model

import com.google.gson.annotations.SerializedName

data class QuestionRequest (
    @SerializedName("ques")
    val ques:String,
    @SerializedName("ans")
    val ans:String
)
