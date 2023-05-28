package com.example.submissionjetpack.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionjetpack.data.IndomieRepository
import com.example.submissionjetpack.model.Indomie
import com.example.submissionjetpack.model.OrderIndomie
import com.example.submissionjetpack.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailIndomieViewModel(
    private val repository: IndomieRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderIndomie>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderIndomie>>
        get() = _uiState

    fun getIndomieById(indomieId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderindomieById(indomieId))
        }
    }

    fun addToCart(indomie: Indomie, count: Int) {
        viewModelScope.launch {
            repository.updateOrderIndomie(indomie.id, count)
        }
    }
}