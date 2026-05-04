package com.umc.workbook.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.workbook.domain.repository.StoreRepository
import com.umc.workbook.model.HomeShoeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val homeItems: List<HomeShoeItem> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storeRepository: StoreRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun loadHomeItems() {
        viewModelScope.launch {
            storeRepository.seedIfEmpty()
            storeRepository.homeItemsFlow().collect { items ->
                _uiState.value = _uiState.value.copy(homeItems = items)
            }
        }
    }
}
