package com.umc.workbook.ui.bag

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umc.workbook.R

/**
 * Replaces fragment_bag.xml (ConstraintLayout → Box):
 *   - iv_bag_empty: 60x60dp, marginTop=250dp, centered
 *     → size(60dp), Box with TopCenter alignment + padding(top=250dp)
 *   - tv_bag_empty_message: 14sp, black, gravity=center, marginTop=27dp
 *     → fontSize=14sp, onBackground, TextAlign.Center, Spacer(27dp)
 *   - btn_order: MaterialButton, height=60dp, margin 24dp sides/bottom, cornerRadius=30dp
 *     backgroundTint=@color/black, textColor=@color/white, textSize=16sp
 *     → Button with RoundedCornerShape(30dp), containerColor=primary, contentColor=onPrimary
 *
 * [BagFragment.btnOrder.setOnClickListener → navigateToBottomTab(nav_purchase)]
 * is now passed as onNavigateToPurchase lambda.
 */
@Composable
fun BagScreen(
    onNavigateToPurchase: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 250.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_bag_circle),
                contentDescription = stringResource(R.string.nav_bag),
                modifier = Modifier.size(60.dp),
            )
            Spacer(modifier = Modifier.height(27.dp))
            Text(
                text = stringResource(R.string.bag_empty_message),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
            )
        }

        Button(
            onClick = onNavigateToPurchase,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
                .height(60.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
        ) {
            Text(
                text = stringResource(R.string.bag_order),
                fontSize = 16.sp,
            )
        }
    }
}
