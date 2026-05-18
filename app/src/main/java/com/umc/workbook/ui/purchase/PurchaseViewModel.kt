package com.umc.workbook.ui.purchase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.umc.workbook.data.AppDataStore
import com.umc.workbook.model.PurchaseProductItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PurchaseViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext

    private val _purchaseItems = MutableStateFlow<List<PurchaseProductItem>>(emptyList())
    val purchaseItems: StateFlow<List<PurchaseProductItem>> = _purchaseItems

    init {
        viewModelScope.launch {
            AppDataStore.purchaseItemsFlow(context).collect { _purchaseItems.value = it }
        }
    }

    fun toggleWish(item: PurchaseProductItem) {
        viewModelScope.launch {
            AppDataStore.togglePurchaseWish(context, item)
        }
    }
}
