package com.c241ps294.sikarir.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.c241ps294.sikarir.data.remote.response.QuizItem
import com.c241ps294.sikarir.data.remote.response.QuizResponse
import com.c241ps294.sikarir.data.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuizRepository(private val apiService: ApiService) {

    suspend fun postQuiz(answerHashMap: HashMap<Int, String>, token: String): QuizResponse {
        return apiService.postQuiz(token = "Bearer $token", answerHashMap = answerHashMap)
    }

    suspend fun getQuizHistory(token: String) : LiveData<List<QuizItem>> {
        val quizzes = MutableLiveData<List<QuizItem>>()
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getQuizHistory("Bearer $token")
                quizzes.postValue(response.quizzes)
            } catch (e: Exception) {
                quizzes.postValue(emptyList())
            }
        }
        return quizzes
    }

    companion object {
        @Volatile
        private var instance: QuizRepository? = null
        fun getInstance(apiService: ApiService): QuizRepository = instance ?: synchronized(this) {
            instance ?: QuizRepository(apiService) }.also { instance = it }
    }
}