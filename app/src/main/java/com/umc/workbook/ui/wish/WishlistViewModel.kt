package com.umc.workbook.ui.wish

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.umc.workbook.data.AppDataStore
import com.umc.workbook.model.PurchaseProductItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WishlistViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext

    private val _wishlistItems = MutableStateFlow<List<PurchaseProductItem>>(emptyList())
    val wishlistItems: StateFlow<List<PurchaseProductItem>> = _wishlistItems

    init {
        viewModelScope.launch {
            AppDataStore.wishlistItemsFlow(context).collect { _wishlistItems.value = it }
        }
    }
}
