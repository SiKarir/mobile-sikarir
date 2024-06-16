package com.c241ps294.sikarir.data.local.quiz

import com.c241ps294.sikarir.data.local.storage.AnswerStorage

data class VerbalReasoning (
    val no: Int,
    val info1: String,
    val info2: String,
    val info3: String,
    val info4: String,
    val info5: String,
    val info6: String,
    val info7: String?,
    val info8: String?,
    val questionText: String,
    val option1: String,
    val option2: String,
    val option3: String,
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
            else -> -1
        }
    }
}