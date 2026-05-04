package com.umc.workbook.ui.wish

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

data class WishlistUiState(
    val wishlistItems: List<PurchaseProductItem> = emptyList()
)

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val storeRepository: StoreRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WishlistUiState())
    val uiState: StateFlow<WishlistUiState> = _uiState.asStateFlow()

    fun loadWishlistItems() {
        viewModelScope.launch {
            storeRepository.wishlistItemsFlow().collect { items ->
                _uiState.value = _uiState.value.copy(wishlistItems = items)
            }
        }
    }
}
