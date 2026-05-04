package com.umc.workbook.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umc.workbook.R

/**
 * Replaces fragment_home.xml (ConstraintLayout → Column):
 *   - text_title: marginStart=41dp, marginTop=44dp, 28sp bold, @color/black
 *     → padding(start=41dp, top=44dp), fontSize=28sp, Bold, colorScheme.onBackground
 *   - text_date: marginStart=41dp, marginTop=10dp below title, 16sp, @color/gray100
 *     → padding(start=41dp, top=10dp), fontSize=16sp, colorScheme.onSurfaceVariant
 *   - ImageView (img_home_main): margin 24dp sides, marginTop=50dp below date, centerCrop
 *     → fillMaxWidth + padding(horizontal=24dp, top=50dp, bottom=24dp), ContentScale.Crop
 */
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Text(
            text = stringResource(R.string.home_discover),
            modifier = Modifier.padding(start = 41.dp, top = 44.dp),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(R.string.home_date),
            modifier = Modifier.padding(start = 41.dp, top = 10.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 16.sp,
        )
        Image(
            painter = painterResource(R.drawable.img_home_main),
            contentDescription = stringResource(R.string.home_main_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 50.dp, bottom = 24.dp),
        )
    }
}
