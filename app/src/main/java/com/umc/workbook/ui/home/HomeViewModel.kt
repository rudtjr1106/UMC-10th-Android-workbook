package com.umc.workbook.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.umc.workbook.data.AppDataStore
import com.umc.workbook.model.HomeShoeItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext

    private val _shoes = MutableStateFlow<List<HomeShoeItem>>(emptyList())
    val shoes: StateFlow<List<HomeShoeItem>> = _shoes

    init {
        viewModelScope.launch {
            AppDataStore.seedIfEmpty(context)
            AppDataStore.homeItemsFlow(context).collect { _shoes.value = it }
        }
    }
}
