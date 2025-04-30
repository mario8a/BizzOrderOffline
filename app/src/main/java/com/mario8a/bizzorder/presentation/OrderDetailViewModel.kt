package com.mario8a.bizzorder.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mario8a.bizzorder.domain.Order
import com.mario8a.bizzorder.domain.OrderRepository
import com.mario8a.bizzorder.presentation.navigation.Screen.DetailOrder.ARG_ORDER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailState(
    val isLoading: Boolean = true,
    val order: Order? = null,
    val isError: Boolean = false
)

@HiltViewModel
class OrderDetailViewModel @Inject constructor (
    ordersRepository: OrderRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val orderId: String? = savedStateHandle.get<String>(ARG_ORDER_ID)

    var detailState = MutableStateFlow(DetailState())
        private set

    init {
        viewModelScope.launch {
            orderId?.let { id ->
                ordersRepository.getOrderById(id)
                    .onSuccess { order ->
                        detailState.value = DetailState(isLoading = false, order = order)
                    }
                    .onFailure {
                        detailState.value = DetailState(isLoading = false, isError = true)
                    }
            } ?: run {
                detailState.value = DetailState(isLoading = false, isError = true)
            }
        }
    }
}

