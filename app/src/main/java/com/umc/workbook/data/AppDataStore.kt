package com.umc.workbook.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.umc.workbook.R
import com.umc.workbook.model.HomeShoeItem
import com.umc.workbook.model.PurchaseProductItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "workbook_store")

object AppDataStore {

    private val gson = Gson()

    private val SEEDED_KEY = booleanPreferencesKey("seeded")
    private val HOME_ITEMS_KEY = stringPreferencesKey("home_items")
    private val PURCHASE_ITEMS_KEY = stringPreferencesKey("purchase_items")
    private val WISHLIST_ITEMS_KEY = stringPreferencesKey("wishlist_items")

    val initialHomeItems = listOf(
        HomeShoeItem(R.drawable.img_shoes_2, "Air Jordan XXVI", "US$185"),
        HomeShoeItem(R.drawable.img_shoes_3, "Nike Dunk Low", "US$170"),
        HomeShoeItem(R.drawable.img_shoes_4, "Nike Air Max", "US$190")
    )

    val initialPurchaseItems = listOf(
        PurchaseProductItem(
            imageResId = R.drawable.img_sock,
            badge = false,
            isWish = true,
            name = "Nike Everyday Plus",
            desc = "Training Ankle Socks (6 Pairs)",
            colors = "5 Colours",
            price = "US$10"
        ),
        PurchaseProductItem(
            imageResId = R.drawable.img_sock,
            badge = false,
            isWish = false,
            name = "Nike Elite Crew",
            desc = "Basketball Socks",
            colors = "7 Colours",
            price = "US$16"
        ),
        PurchaseProductItem(
            imageResId = R.drawable.img_shoes_2,
            badge = true,
            isWish = false,
            name = "Nike Air Force 1 '07",
            desc = "Women's Shoes",
            colors = "5 Colours",
            price = "US$115"
        ),
        PurchaseProductItem(
            imageResId = R.drawable.img_shoes_3,
            badge = true,
            isWish = false,
            name = "Jordan ENike Air Force 1 '07ssentials",
            desc = "Men's Shoes",
            colors = "2 Colours",
            price = "US$115"
        )
    )

    val initialWishlistItems = listOf(
        PurchaseProductItem(
            imageResId = R.drawable.img_sock,
            badge = false,
            isWish = true,
            name = "Air Jordan 1 Mid",
            desc = "",
            colors = "",
            price = "US$125"
        ),
        PurchaseProductItem(
            imageResId = R.drawable.img_shoes_2,
            badge = false,
            isWish = true,
            name = "Nike Everyday Plus Cushioned",
            desc = "Training Ankle Socks (6 Pairs)",
            colors = "5 Colours",
            price = "US$10"
        ),
        PurchaseProductItem(
            imageResId = R.drawable.img_shoes_3,
            badge = true,
            isWish = true,
            name = "Nike Air Force 1 '07",
            desc = "Women's Shoes",
            colors = "5 Colours",
            price = "US$115"
        ),
        PurchaseProductItem(
            imageResId = R.drawable.img_shoes_4,
            badge = true,
            isWish = true,
            name = "Jordan Essentials",
            desc = "Men's Shoes",
            colors = "2 Colours",
            price = "US$115"
        )
    )

    suspend fun seedIfEmpty(context: Context) {
        context.dataStore.edit { preferences ->
            if (preferences[SEEDED_KEY] == true) return@edit

            preferences[HOME_ITEMS_KEY] = encodeHomeItems(initialHomeItems)
            preferences[PURCHASE_ITEMS_KEY] = encodePurchaseItems(initialPurchaseItems)
            preferences[WISHLIST_ITEMS_KEY] = encodePurchaseItems(initialWishlistItems)
            preferences[SEEDED_KEY] = true
        }
    }

    fun homeItemsFlow(context: Context): Flow<List<HomeShoeItem>> {
        return context.dataStore.data.map { preferences ->
            decodeHomeItems(preferences[HOME_ITEMS_KEY])
        }
    }

    fun purchaseItemsFlow(context: Context): Flow<List<PurchaseProductItem>> {
        return context.dataStore.data.map { preferences ->
            decodePurchaseItems(preferences[PURCHASE_ITEMS_KEY])
        }
    }

    fun wishlistItemsFlow(context: Context): Flow<List<PurchaseProductItem>> {
        return context.dataStore.data.map { preferences ->
            decodePurchaseItems(preferences[WISHLIST_ITEMS_KEY])
        }
    }

    suspend fun togglePurchaseWish(context: Context, productItem: PurchaseProductItem) {
        context.dataStore.edit { preferences ->
            val updatedPurchaseItems = decodePurchaseItems(preferences[PURCHASE_ITEMS_KEY]).map { storedItem ->
                if (storedItem.name == productItem.name) {
                    storedItem.copy(isWish = !storedItem.isWish)
                } else {
                    storedItem
                }
            }
            preferences[PURCHASE_ITEMS_KEY] = encodePurchaseItems(updatedPurchaseItems)
            preferences[WISHLIST_ITEMS_KEY] = encodePurchaseItems(updatedPurchaseItems.filter { it.isWish })
        }
    }

    private fun encodeHomeItems(items: List<HomeShoeItem>): String {
        return gson.toJson(items)
    }

    private fun decodeHomeItems(raw: String?): List<HomeShoeItem> {
        if (raw.isNullOrBlank()) return emptyList()
        // TypeToken은 단순 오브젝트가 아니라 제네릭이 포함된 List는
        // List로만 보여지는 경우가 있기 때문에 타입 확정을 위해 넣은 class 입니다.
        val type = object : TypeToken<List<HomeShoeItem>>() {}.type
        return gson.fromJson(raw, type) ?: emptyList()
    }

    private fun encodePurchaseItems(items: List<PurchaseProductItem>): String {
        return gson.toJson(items)
    }

    private fun decodePurchaseItems(raw: String?): List<PurchaseProductItem> {
        if (raw.isNullOrBlank()) return emptyList()
        val type = object : TypeToken<List<PurchaseProductItem>>() {}.type
        return gson.fromJson(raw, type) ?: emptyList()
    }
}
