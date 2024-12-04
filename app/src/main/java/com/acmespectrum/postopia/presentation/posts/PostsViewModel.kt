package com.example.postopia.presentation.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postopia.data.response.post.Post
import com.example.postopia.data.response.post.PostsListModel
import com.example.postopia.domain.PostsUseCases
import com.example.postopia.utils.ApiStatus
import kotlinx.coroutines.launch

class PostsViewModel(private val postsUseCases: PostsUseCases) : ViewModel() {
    private val _allPostsResult = MutableLiveData<Result<PostsListModel>>()
    val allPostsResult: LiveData<Result<PostsListModel>> get() = _allPostsResult

    private val _newPostResult = MutableLiveData<Result<Post>>()
    val newPostResult: LiveData<Result<Post>> get() = _newPostResult

    private val _apiStatus = MutableLiveData<ApiStatus>()
    val apiStatus: LiveData<ApiStatus> get() = _apiStatus

    fun getAllPosts() {
        viewModelScope.launch {
            try {
                _apiStatus.postValue(ApiStatus.LOADING)

                val result = postsUseCases.getAllPosts()

                _allPostsResult.postValue(result)

                if (result.isSuccess)
                    _apiStatus.postValue(ApiStatus.SUCCESS)
                else
                    _apiStatus.postValue(ApiStatus.ERROR)

            } catch (e: Exception) {
                _apiStatus.postValue(ApiStatus.ERROR)
            }
        }
    }

    fun createNewPost(user: String, description: String, image: String) {
        viewModelScope.launch {
            try {
                _apiStatus.postValue(ApiStatus.LOADING)

                val result = postsUseCases.createNewPos(user, description, image)

                _newPostResult.postValue(result)

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