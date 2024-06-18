package com.c241ps294.sikarir.data.local.storage

object AnswerStorage {
    private val answers: MutableMap<Int, String> = mutableMapOf()

    fun addOrUpdateAnswer(key: Int, answer: String) {
        answers[key] = answer
    }

    fun getAnswers(): HashMap<Int, String> {
        return HashMap(answers)
    }

    fun clearAnswers() {
        answers.clear()
    }
}
