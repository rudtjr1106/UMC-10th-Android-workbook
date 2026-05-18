package com.umc.workbook.ui.purchase

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umc.workbook.R
import com.umc.workbook.model.PurchaseProductItem
import com.umc.workbook.model.PurchaseTab

@Composable
fun PurchaseScreen(
    purchaseItems: List<PurchaseProductItem>,
    onWishClick: (PurchaseProductItem) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(PurchaseTab.SHOES) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 24.dp)
    ) {
        PurchaseTabRow(
            selectedTab = selectedTab,
            onTabSelect = { selectedTab = it }
        )

        when (selectedTab) {
            PurchaseTab.SHOES -> PurchaseProductGrid(
                items = purchaseItems,
                onWishClick = onWishClick
            )
            PurchaseTab.TOPS, PurchaseTab.SALE -> PurchaseEmptyContent()
        }
    }
}

@Composable
private fun PurchaseTabRow(
    selectedTab: PurchaseTab,
    onTabSelect: (PurchaseTab) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = stringResource(R.string.purchase_tab_all),
            color = if (selectedTab == PurchaseTab.SHOES) Color.Black else Color(0xFF7A7A7A),
            fontSize = 16.sp,
            modifier = Modifier.clickable { onTabSelect(PurchaseTab.SHOES) }
        )
        Spacer(modifier = Modifier.width(30.dp))
        Text(
            text = stringResource(R.string.purchase_tab_tops),
            color = if (selectedTab == PurchaseTab.TOPS) Color.Black else Color(0xFF7A7A7A),
            fontSize = 16.sp,
            modifier = Modifier.clickable { onTabSelect(PurchaseTab.TOPS) }
        )
        Spacer(modifier = Modifier.width(30.dp))
        Text(
            text = stringResource(R.string.purchase_tab_sale),
            color = if (selectedTab == PurchaseTab.SALE) Color.Black else Color(0xFF7A7A7A),
            fontSize = 16.sp,
            modifier = Modifier.clickable { onTabSelect(PurchaseTab.SALE) }
        )
    }
}

@Composable
private fun PurchaseProductGrid(
    items: List<PurchaseProductItem>,
    onWishClick: (PurchaseProductItem) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 24.dp, top = 32.dp, end = 24.dp, bottom = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            PurchaseProductCard(item = item, onWishClick = onWishClick)
        }
    }
}

@Composable
private fun PurchaseEmptyContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.purchase_empty_tab_message),
            color = Color(0xFF7A7A7A),
            fontSize = 16.sp
        )
    }
}

@Composable
fun PurchaseProductCard(
    item: PurchaseProductItem,
    onWishClick: (PurchaseProductItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Box {
            Image(
                painter = painterResource(item.imageResId),
                contentDescription = stringResource(R.string.purchase_product_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                contentScale = ContentScale.Fit,
                alignment = Alignment.TopStart
            )
            Icon(
                painter = painterResource(
                    if (item.isWish) R.drawable.ic_heart_circle_active
                    else R.drawable.ic_heart_circle
                ),
                contentDescription = stringResource(R.string.purchase_wish_icon),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 12.dp, end = 12.dp)
                    .size(24.dp)
                    .clickable { onWishClick(item) },
                tint = Color.Unspecified
            )
        }
        if (item.badge) {
            Text(
                text = "Best Seller",
                color = Color(0xFFFF6A00),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
        Text(
            text = item.name,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = if (item.badge) 0.dp else 12.dp)
        )
        if (item.desc.isNotEmpty()) {
            Text(
                text = item.desc,
                color = Color(0xFF7A7A7A),
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
        if (item.colors.isNotEmpty()) {
            Text(
                text = item.colors,
                color = Color(0xFF7A7A7A),
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
        Text(
            text = item.price,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}
