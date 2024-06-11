package com.c241ps294.sikarir.ui.authentication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.c241ps294.sikarir.data.preference.user.User
import com.c241ps294.sikarir.data.remote.request.LoginRequest
import com.c241ps294.sikarir.data.remote.request.RegisterRequest
import com.c241ps294.sikarir.data.remote.response.LoginResponse
import com.c241ps294.sikarir.data.remote.response.RegisterResponse
import com.c241ps294.sikarir.data.remote.retrofit.ApiConfig
import com.c241ps294.sikarir.data.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthenticationViewModel (private val userRepository: UserRepository) : ViewModel() {

    private val _loginUser = MutableLiveData<LoginResponse>()
    val loginUser: LiveData<LoginResponse> = _loginUser

    private val _registerUser = MutableLiveData<RegisterResponse>()
    val registerUser: LiveData<RegisterResponse> = _registerUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun login (username: String, password: String) {
        _isLoading.value = true
        val loginRequest = LoginRequest(username, password)
        val client = ApiConfig.getApiService().login(loginRequest)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _loginUser.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun register (username: String, name: String, email: String, password: String) {
        _isLoading.value = true
        val registerRequest = RegisterRequest(username, name, email, password)
        val client = ApiConfig.getApiService().register(registerRequest)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _registerUser.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun saveSession(user: User) {
        viewModelScope.launch {
            userRepository.saveSession(user)
        }
    }

    fun getSession() : LiveData<User> {
        return userRepository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
        }
    }

    companion object{
        private const val TAG = "AuthenticationViewModel"
    }
}