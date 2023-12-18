package com.example.challengcodingapp.ui.home

import androidx.lifecycle.viewModelScope
import com.example.challengcodingapp.base.BaseViewModel
import com.example.challengcodingapp.data.netowork.Resource
import com.example.challengcodingapp.data.repo.ApiRepo
import com.example.challengcodingapp.ui.uiState.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ApiRepo,
): BaseViewModel<UiState>() {

    init {
        getItems()
    }

    private fun getItems() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            when(val res = repo.getItems()) {
                is Resource.Failure -> _uiState.value = UiState.Fail(res.message)
                is Resource.Success -> _uiState.value = UiState.Success(res.value)
            }
        }
    }

    fun onRetry() = getItems()

}