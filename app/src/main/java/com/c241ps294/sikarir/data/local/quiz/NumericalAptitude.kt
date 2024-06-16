package com.c241ps294.sikarir.data.local.quiz

import com.c241ps294.sikarir.data.local.storage.AnswerStorage

data class NumericalAptitude (
    val no: Int,
    val questionText: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val option5: String,
    private var _userAnswer: String = ""
) {
    var userAnswer: String
        get() = _userAnswer
        set(value) {
            _userAnswer = value
            AnswerStorage.addOrUpdateAnswer(no, convertAnswerToInt(value))
        }

    private fun convertAnswerToInt(answer: String): Int {
        return when (answer) {
            "0" -> 0
            "1" -> 1
            "2" -> 2
            "3" -> 3
            "4" -> 4
            else -> -1
        }
    }
}