package com.example.postopia.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postopia.data.response.user.UserModel
import com.example.postopia.domain.AuthUseCases
import kotlinx.coroutines.launch

class AuthViewModel(private val authUseCases: AuthUseCases) : ViewModel() {

    private val _userResult = MutableLiveData<Result<UserModel>>()
    val userResult: LiveData<Result<UserModel>> get() = _userResult

    private val _apiStatus = MutableLiveData<ApiStatus>()
    val apiStatus: LiveData<ApiStatus> get() = _apiStatus

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                _apiStatus.postValue(ApiStatus.LOADING)

                val result = authUseCases.register(name, email, password)

                _userResult.postValue(result)

                if (result.isSuccess)
                    _apiStatus.postValue(ApiStatus.SUCCESS)
                else
                    _apiStatus.postValue(ApiStatus.ERROR)

            } catch (e: Exception) {
                _apiStatus.postValue(ApiStatus.ERROR)
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _apiStatus.postValue(ApiStatus.LOADING)

                val result = authUseCases.login(email, password)

                _userResult.postValue(result)

                if (result.isSuccess)
                    _apiStatus.postValue(ApiStatus.SUCCESS)
                else
                    _apiStatus.postValue(ApiStatus.ERROR)

            } catch (e: Exception) {
                _apiStatus.postValue(ApiStatus.ERROR)
            }
        }
    }

}

enum class ApiStatus {
    INITIAL,
    LOADING,
    SUCCESS,
    ERROR
}