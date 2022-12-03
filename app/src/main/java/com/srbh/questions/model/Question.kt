package com.srbh.questions.model

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("id")
    val id:Int,
    @SerializedName("ques")
    val ques:String,
    @SerializedName("ans")
    val ans:String
)
