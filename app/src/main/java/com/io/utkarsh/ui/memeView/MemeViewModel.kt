package com.io.utkarsh.ui.memeView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import com.io.utkarsh.data.model.MemeApiResponseModel
import com.io.utkarsh.data.repository.MemeRepository
import com.io.utkarsh.ui.base.UiState

class MemeViewModel(private val topHeadlineRepository: MemeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<MemeApiResponseModel>>(UiState.Loading)

    val uiState: StateFlow<UiState<MemeApiResponseModel>> = _uiState



    public fun fetchMemeApi() {
        viewModelScope.launch {
            topHeadlineRepository.getMemeApiResponse()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}