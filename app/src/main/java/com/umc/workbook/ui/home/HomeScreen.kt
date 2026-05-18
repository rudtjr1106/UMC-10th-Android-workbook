package com.umc.workbook.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umc.workbook.R
import com.umc.workbook.model.HomeShoeItem
import com.umc.workbook.ui.theme.Gray100
import com.umc.workbook.ui.theme.Gray600
import com.umc.workbook.ui.theme.WorkbookTheme

private val shoes = listOf(
    HomeShoeItem(R.drawable.img_shoes_2, "Air Jordan XXVI", "US\$185"),
    HomeShoeItem(R.drawable.img_shoes_3, "Nike Dunk Low", "US\$170"),
    HomeShoeItem(R.drawable.img_shoes_4, "Nike Air Max", "US\$190")
)

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 24.dp)
    ) {
        Text(
            text = stringResource(R.string.home_discover),
            color = Color.Black,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 41.dp, top = 44.dp)
        )
        Text(
            text = stringResource(R.string.home_date),
            color = Gray100,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 41.dp, top = 10.dp)
        )
        Image(
            painter = painterResource(R.drawable.img_home_main),
            contentDescription = stringResource(R.string.home_main_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 50.dp, bottom = 24.dp)
        )
        Text(
            text = stringResource(R.string.home_whats_new),
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 42.dp, top = 40.dp)
        )
        Text(
            text = stringResource(R.string.home_latest_title),
            color = Gray600,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 42.dp, top = 12.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(start = 42.dp, end = 24.dp),
            modifier = Modifier.padding(top = 22.dp)
        ) {
            items(shoes) { shoe ->
                HomeShoeCard(shoe)
            }
        }
    }
}

@Composable
fun HomeShoeCard(item: HomeShoeItem) {
    Column(modifier = Modifier.width(320.dp)) {
        Image(
            painter = painterResource(item.imageResId),
            contentDescription = stringResource(R.string.home_shoe_image),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        )
        Text(
            text = item.name,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 14.dp)
        )
        Text(
            text = item.price,
            color = Gray100,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 8.dp, bottom = 6.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    WorkbookTheme { HomeScreen() }
}
