package com.c241ps294.sikarir.data.remote.response

import com.google.gson.annotations.SerializedName

data class QuizResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("recommendation")
    val recommendation: List<ListCareerItem>,

    @field:SerializedName("quizId")
    val quizId: String,

    @field:SerializedName("quizDate")
    val quizDate: String
)