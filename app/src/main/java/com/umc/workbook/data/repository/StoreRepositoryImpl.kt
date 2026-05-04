package com.umc.workbook.data.repository

import android.content.Context
import com.umc.workbook.data.AppDataStore
import com.umc.workbook.domain.repository.StoreRepository
import com.umc.workbook.model.HomeShoeItem
import com.umc.workbook.model.PurchaseProductItem
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : StoreRepository {

    override suspend fun seedIfEmpty() {
        AppDataStore.seedIfEmpty(context)
    }

    override fun homeItemsFlow(): Flow<List<HomeShoeItem>> {
        return AppDataStore.homeItemsFlow(context)
    }

    override fun purchaseItemsFlow(): Flow<List<PurchaseProductItem>> {
        return AppDataStore.purchaseItemsFlow(context)
    }

    override fun wishlistItemsFlow(): Flow<List<PurchaseProductItem>> {
        return AppDataStore.wishlistItemsFlow(context)
    }

    override suspend fun togglePurchaseWish(productItem: PurchaseProductItem) {
        AppDataStore.togglePurchaseWish(context, productItem)
    }
}
