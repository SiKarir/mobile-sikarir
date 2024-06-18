package com.c241ps294.sikarir.data.remote.response

import com.google.gson.annotations.SerializedName

data class QuizHistoryResponse (
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("quizzes")
    val quizzes: List<QuizItem>,
)

data class QuizItem (

    @field:SerializedName("quizId")
    val quizId: String,

    @field:SerializedName("quizDate")
    val quizDate: String,

    @field:SerializedName("recommendation")
    val recommendation: List<ListCareerItem>,

    @field:SerializedName("userId")
    val userId: String,
)