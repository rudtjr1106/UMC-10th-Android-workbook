package com.umc.workbook.ui.purchase

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umc.workbook.R
import com.umc.workbook.model.PurchaseProductItem
import com.umc.workbook.ui.theme.Gray100
import com.umc.workbook.ui.theme.WorkbookTheme

private val purchaseItems = listOf(
    PurchaseProductItem(
        imageResId = R.drawable.img_sock,
        badge = false,
        isWish = true,
        name = "Nike Everyday Plus",
        desc = "Training Ankle Socks (6 Pairs)",
        colors = "5 Colours",
        price = "US\$10"
    ),
    PurchaseProductItem(
        imageResId = R.drawable.img_sock,
        badge = false,
        isWish = false,
        name = "Nike Elite Crew",
        desc = "Basketball Socks",
        colors = "7 Colours",
        price = "US\$16"
    ),
    PurchaseProductItem(
        imageResId = R.drawable.img_shoes_2,
        badge = true,
        isWish = false,
        name = "Nike Air Force 1 '07",
        desc = "Women's Shoes",
        colors = "5 Colours",
        price = "US\$115"
    ),
    PurchaseProductItem(
        imageResId = R.drawable.img_shoes_3,
        badge = true,
        isWish = false,
        name = "Jordan Essentials",
        desc = "Men's Shoes",
        colors = "2 Colours",
        price = "US\$115"
    )
)

@Composable
fun PurchaseScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 24.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.purchase_tab_all),
                color = Color.Black,
                fontSize = 16.sp
            )
            Text(
                text = stringResource(R.string.purchase_tab_tops),
                color = Gray100,
                fontSize = 16.sp
            )
            Text(
                text = stringResource(R.string.purchase_tab_sale),
                color = Gray100,
                fontSize = 16.sp
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(purchaseItems) { item ->
                PurchaseProductCard(item)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PurchaseScreenPreview() {
    WorkbookTheme { PurchaseScreen() }
}
