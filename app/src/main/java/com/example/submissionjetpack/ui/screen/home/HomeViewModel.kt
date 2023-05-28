package com.example.submissionjetpack.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionjetpack.data.IndomieRepository
import com.example.submissionjetpack.model.OrderIndomie
import com.example.submissionjetpack.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: IndomieRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderIndomie>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderIndomie>>>
        get() = _uiState

    fun getAllIndomie() {
        viewModelScope.launch {
            repository.getAllIndomie()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderIndomie ->
                    _uiState.value = UiState.Success(orderIndomie)
                }
        }
    }
}