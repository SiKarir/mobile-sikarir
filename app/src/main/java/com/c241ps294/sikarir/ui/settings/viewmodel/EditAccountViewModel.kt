package com.c241ps294.sikarir.ui.settings.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.c241ps294.sikarir.data.remote.response.EditAccountResponse
import com.c241ps294.sikarir.data.remote.retrofit.ApiConfig
import com.c241ps294.sikarir.data.repository.UserRepository
import kotlinx.coroutines.flow.first
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditAccountViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _editAccount = MutableLiveData<EditAccountResponse>()
    val editAccount: LiveData<EditAccountResponse> = _editAccount

    suspend fun editAccount(username: RequestBody, name: RequestBody, email: RequestBody, password: RequestBody, file: MultipartBody.Part){
        _isLoading.value = true
        val token = userRepository.getSession().first().token
        val authorization = "Bearer $token"
        val client = ApiConfig.getApiService().editAccount(authorization, username = username, name = name, email = email, password = password, file = file)
        client.enqueue(object : Callback<EditAccountResponse> {
            override fun onResponse(
                call: Call<EditAccountResponse>,
                response: Response<EditAccountResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _editAccount.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<EditAccountResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })


}
    companion object{
        private const val TAG = "EditAccountViewModel"
    }
}

