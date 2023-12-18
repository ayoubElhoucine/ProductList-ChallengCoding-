package com.example.challengcodingapp.ui.details

import android.app.Application
import com.example.challengcodingapp.base.BaseViewModel
import com.example.challengcodingapp.ui.uiState.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val application: Application,
): BaseViewModel<UiState>() {
    val expandDetails: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun toggleExpand() {
        expandDetails.value = !expandDetails.value
    }
}