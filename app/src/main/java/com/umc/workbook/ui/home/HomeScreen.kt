package com.umc.workbook.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umc.workbook.R
import com.umc.workbook.model.HomeShoeItem

@Composable
fun HomeScreen(
    shoes: List<HomeShoeItem>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 24.dp)
    ) {
        Text(
            text = stringResource(R.string.home_discover),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 41.dp, top = 44.dp)
        )
        Text(
            text = stringResource(R.string.home_date),
            fontSize = 16.sp,
            color = Color(0xFF7A7A7A),
            modifier = Modifier.padding(start = 41.dp, top = 10.dp)
        )
        Image(
            painter = painterResource(R.drawable.img_home_main),
            contentDescription = stringResource(R.string.home_main_image),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 50.dp, end = 24.dp),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = stringResource(R.string.home_whats_new),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 42.dp, top = 40.dp)
        )
        Text(
            text = stringResource(R.string.home_latest_title),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF767676),
            modifier = Modifier.padding(start = 42.dp, top = 12.dp)
        )
        Spacer(modifier = Modifier.height(22.dp))
        LazyRow(
            contentPadding = PaddingValues(start = 42.dp, end = 24.dp)
        ) {
            items(shoes) { shoe ->
                HomeShoeCard(shoe = shoe)
            }
        }
    }
}

@Composable
private fun HomeShoeCard(shoe: HomeShoeItem) {
    Column(modifier = Modifier.width(320.dp)) {
        Image(
            painter = painterResource(shoe.imageResId),
            contentDescription = stringResource(R.string.home_shoe_image),
            modifier = Modifier
                .width(320.dp)
                .height(260.dp),
            contentScale = ContentScale.Fit,
            alignment = androidx.compose.ui.Alignment.TopStart
        )
        Text(
            text = shoe.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 14.dp)
        )
        Text(
            text = shoe.price,
            fontSize = 16.sp,
            color = Color(0xFF7A7A7A),
            modifier = Modifier.padding(top = 8.dp, bottom = 6.dp)
        )
    }
}
