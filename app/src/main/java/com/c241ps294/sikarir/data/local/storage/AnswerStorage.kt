package com.c241ps294.sikarir.data.local.storage

object AnswerStorage {
    private val answers: MutableList<String> = mutableListOf()

    fun addAnswer(answer: String) {
        answers.add(answer)
    }

    fun getAnswers(): List<String> {
        return answers
    }

    fun clearAnswers() {
        answers.clear()
    }
}
