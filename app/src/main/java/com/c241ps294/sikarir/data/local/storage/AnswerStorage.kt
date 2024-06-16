package com.c241ps294.sikarir.data.local.storage

object AnswerStorage {
    private val answers: MutableMap<Int, Int> = mutableMapOf()

    fun addOrUpdateAnswer(key: Int, answer: Int) {
        answers[key] = answer
    }

    fun getAnswers(): Map<Int, Int> {
        return answers
    }

    fun clearAnswers() {
        answers.clear()
    }
}
