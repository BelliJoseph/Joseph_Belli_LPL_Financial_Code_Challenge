package com.example.lplfinancialcodechallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lplfinancialcodechallenge.data.domain.PostDomainModel
import com.example.lplfinancialcodechallenge.domain.GetPostsUseCase
import com.example.lplfinancialcodechallenge.domain.UiState
import com.example.lplfinancialcodechallenge.domain.UpdatePostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val updatePostUseCase: UpdatePostUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _postsState: MutableStateFlow<UiState<List<PostDomainModel>>> =
        MutableStateFlow(UiState.LOADING)
    val postsState = _postsState.asStateFlow()

    init {
        viewModelScope.launch(dispatcher) {
            getPosts()
        }
    }

    private suspend fun getPosts() {
        getPostsUseCase.getPosts().collect {
            _postsState.value = it
        }
    }

    fun updatePost(
        post: PostDomainModel,
        imagePath: String
    ) {
        viewModelScope.launch(dispatcher) {
            updatePostUseCase.updatePost(
                post = post,
                imagePath = imagePath
            )

            getPosts()
        }
    }
}