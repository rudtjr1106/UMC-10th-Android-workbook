package com.umc.workbook.model

data class PurchaseProductItem(
    val imageResId: Int,
    val badge: Boolean,
    val isWish: Boolean,
    val name: String,
    val desc: String,
    val colors: String,
    val price: String
)