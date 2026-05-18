package com.umc.workbook.ui.wish

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umc.workbook.R
import com.umc.workbook.model.PurchaseProductItem
import com.umc.workbook.ui.purchase.PurchaseProductCard
import com.umc.workbook.ui.theme.WorkbookTheme

private val wishlistItems = listOf(
    PurchaseProductItem(
        imageResId = R.drawable.img_sock,
        badge = false,
        isWish = true,
        name = "Air Jordan 1 Mid",
        desc = "",
        colors = "",
        price = "US\$125"
    ),
    PurchaseProductItem(
        imageResId = R.drawable.img_shoes_2,
        badge = false,
        isWish = true,
        name = "Nike Everyday Plus Cushioned",
        desc = "Training Ankle Socks (6 Pairs)",
        colors = "5 Colours",
        price = "US\$10"
    ),
    PurchaseProductItem(
        imageResId = R.drawable.img_shoes_3,
        badge = true,
        isWish = true,
        name = "Nike Air Force 1 '07",
        desc = "Women's Shoes",
        colors = "5 Colours",
        price = "US\$115"
    ),
    PurchaseProductItem(
        imageResId = R.drawable.img_shoes_4,
        badge = true,
        isWish = true,
        name = "Jordan Essentials",
        desc = "Men's Shoes",
        colors = "2 Colours",
        price = "US\$115"
    )
)

@Composable
fun WishlistScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 24.dp)
    ) {
        Text(
            text = stringResource(R.string.nav_wishlist),
            color = Color.Black,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(wishlistItems) { item ->
                PurchaseProductCard(item)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WishlistScreenPreview() {
    WorkbookTheme { WishlistScreen() }
}
