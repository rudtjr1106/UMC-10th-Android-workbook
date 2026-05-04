package com.umc.workbook.domain.repository

import com.umc.workbook.model.HomeShoeItem
import com.umc.workbook.model.PurchaseProductItem
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun seedIfEmpty()
    fun homeItemsFlow(): Flow<List<HomeShoeItem>>
    fun purchaseItemsFlow(): Flow<List<PurchaseProductItem>>
    fun wishlistItemsFlow(): Flow<List<PurchaseProductItem>>
    suspend fun togglePurchaseWish(productItem: PurchaseProductItem)
}
