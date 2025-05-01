package com.mario8a.bizzorder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mario8a.bizzorder.domain.PreOrder
import com.mario8a.bizzorder.domain.PreOrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CreateEvent {
    data object Success: CreateEvent()
    data object Error: CreateEvent()
}

@HiltViewModel
class PreOrderViewModel @Inject constructor(
    private val preOrderRepository: PreOrderRepository
): ViewModel() {

    // State flow es usado para almacenar estados de la UI
    // shared flow es usado para notificar eventos
    var eventFlow = MutableSharedFlow<CreateEvent>()
        private set

    fun onSavePreOrder(customerName: String, product: String){
        viewModelScope.launch {
            val result = preOrderRepository.savePreOrder(
                PreOrder(customerName = customerName, product = product)
            )
            if (result.isSuccess) {
                eventFlow.emit(CreateEvent.Success)
            } else {
                eventFlow.emit(CreateEvent.Error)
            }
        }
    }

}