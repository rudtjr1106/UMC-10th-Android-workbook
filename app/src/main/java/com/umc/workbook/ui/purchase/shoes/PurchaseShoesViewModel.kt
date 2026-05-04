package com.umc.workbook.ui.purchase.shoes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.workbook.domain.repository.StoreRepository
import com.umc.workbook.model.PurchaseProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PurchaseShoesUiState(
    val purchaseItems: List<PurchaseProductItem> = emptyList()
)

@HiltViewModel
class PurchaseShoesViewModel @Inject constructor(
    private val storeRepository: StoreRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PurchaseShoesUiState())
    val uiState: StateFlow<PurchaseShoesUiState> = _uiState.asStateFlow()

    fun loadPurchaseItems() {
        viewModelScope.launch {
            storeRepository.purchaseItemsFlow().collect { items ->
                _uiState.value = _uiState.value.copy(purchaseItems = items)
            }
        }
    }

    fun togglePurchaseWish(item: PurchaseProductItem) {
        viewModelScope.launch {
            storeRepository.togglePurchaseWish(item)
        }
    }
}
