package com.c241ps294.sikarir.data.local.quiz

data class PerceptualAptitude (
    val questionText: String,
    val option1: String,
    val option2: String,
    // pertanyaan 6-10 hanya membutuhkan 2 opsi
    val option3: String?,
    val option4: String?,
    val option5: String?,
)