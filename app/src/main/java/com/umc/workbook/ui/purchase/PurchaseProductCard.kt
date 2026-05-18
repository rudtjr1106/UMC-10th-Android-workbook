package com.umc.workbook.ui.purchase

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.umc.workbook.ui.theme.Gray100
import com.umc.workbook.ui.theme.Orange

@Composable
fun PurchaseProductCard(item: PurchaseProductItem) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box {
            Image(
                painter = painterResource(item.imageResId),
                contentDescription = stringResource(R.string.purchase_product_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
            )
            Image(
                painter = painterResource(
                    if (item.isWish) R.drawable.ic_heart_circle_active else R.drawable.ic_heart_circle
                ),
                contentDescription = stringResource(R.string.purchase_wish_icon),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 12.dp, end = 24.dp)
                    .size(24.dp)
            )
        }
        if (item.badge) {
            Text(
                text = "Best Seller",
                color = Orange,
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
            modifier = Modifier.padding(top = 2.dp)
        )
        if (item.desc.isNotEmpty()) {
            Text(
                text = item.desc,
                color = Gray100,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
        if (item.colors.isNotEmpty()) {
            Text(
                text = item.colors,
                color = Gray100,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
        Text(
            text = item.price,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
        )
    }
}
