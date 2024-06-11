package com.c241ps294.sikarir.ui.settings.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.c241ps294.sikarir.data.remote.request.EditAccountRequest
import com.c241ps294.sikarir.data.remote.response.EditAccountResponse
import com.c241ps294.sikarir.data.remote.retrofit.ApiConfig
import com.c241ps294.sikarir.data.repository.UserRepository
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditAccountViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _editAccount = MutableLiveData<EditAccountResponse>()
    val editAccount: LiveData<EditAccountResponse> = _editAccount

    val username: LiveData<String> = userRepository.getSession().map { it.username }.asLiveData()

    suspend fun editAccount(username: String, name: String, email: String, password: String){
        _isLoading.value = true
        val editAccountRequest = EditAccountRequest(username, name, email, password)
        val token = userRepository.getSession().first().token
        val authorization = "Bearer $token"
        val client = ApiConfig.getApiService().editAccount(authorization, editAccountRequest)
        client.enqueue(object : Callback<EditAccountResponse> {
            override fun onResponse(
                call: Call<EditAccountResponse>,
                response: Response<EditAccountResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _editAccount.value = response.body()
                } else {
                    Log.e(EditAccountViewModel.TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<EditAccountResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(EditAccountViewModel.TAG, "onFailure: ${t.message.toString()}")
            }
        })


}
    companion object{
        private const val TAG = "EditAccountViewModel"
    }
}

