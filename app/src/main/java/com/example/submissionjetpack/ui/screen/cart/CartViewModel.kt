package com.example.submissionjetpack.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionjetpack.data.IndomieRepository
import com.example.submissionjetpack.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: IndomieRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderIndomie() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderIndomie()
                .collect { orderIndomie ->
                    val totalPrice =
                        orderIndomie.sumOf { it.indomie.price * it.count }
                    _uiState.value = UiState.Success(CartState(orderIndomie, totalPrice))
                }
        }
    }

    fun updateOrderIndomie(indomieId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderIndomie(indomieId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderIndomie()
                    }
                }
        }
    }
}