package com.mario8a.bizzorder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mario8a.bizzorder.domain.Order
import com.mario8a.bizzorder.domain.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class HomeState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val data: List<Order> = emptyList()
)

@HiltViewModel
class OrderViewModel @Inject constructor(
    orderRepository: OrderRepository
): ViewModel() {

    val homeState: StateFlow<HomeState> = orderRepository.getOrder()
        .distinctUntilChanged()
        .map { result ->
            result.fold(
                onSuccess = { data ->
                    HomeState(
                        isLoading = false,
                        isError = false,
                        data = data
                    )
                },
                onFailure = {
                    HomeState(isError = true, isLoading = false, data = emptyList())
                }
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = HomeState(isLoading = true)
        )
}