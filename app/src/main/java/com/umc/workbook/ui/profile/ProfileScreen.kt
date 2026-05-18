package com.umc.workbook.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.umc.workbook.R
import com.umc.workbook.model.ReqResUser

@Composable
fun ProfileScreen(
    profileUser: ReqResUser?,
    followingUsers: List<ReqResUser>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        ProfileHeader(profileUser = profileUser)

        SectionDivider()

        MemberBenefitSection(email = profileUser?.email)

        SectionDivider()

        FollowingSection(followingUsers = followingUsers)

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .background(Color(0xFFEFEFEF)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.profile_join_date),
                color = Color(0xFF767676),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun SectionDivider() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .background(Color(0xFFEDEDED))
    )
}

@Composable
private fun ProfileHeader(profileUser: ReqResUser?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 48.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = profileUser?.avatar,
            contentDescription = stringResource(R.string.profile_avatar_desc),
            placeholder = painterResource(R.drawable.bg_profile_avatar_placeholder),
            error = painterResource(R.drawable.bg_profile_avatar_placeholder),
            modifier = Modifier
                .size(84.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Text(
            text = profileUser?.let { "${it.firstName} ${it.lastName}" }
                ?: stringResource(R.string.profile_nickname),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 30.dp)
        )

        Box(
            modifier = Modifier
                .padding(top = 30.dp)
                .width(180.dp)
                .height(48.dp)
                .border(1.dp, Color(0xFFD9D9D9), RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.profile_edit),
                color = Color.Black,
                fontSize = 16.sp
            )
        }

        ProfileActionRow(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
    }
}

@Composable
private fun ProfileActionRow(modifier: Modifier = Modifier) {
    val actions = listOf(
        Triple(R.drawable.ic_order, R.string.profile_order, R.string.profile_order_icon_desc),
        Triple(R.drawable.ic_user_card, R.string.profile_pass, R.string.profile_pass_icon_desc),
        Triple(R.drawable.ic_calendar, R.string.profile_event, R.string.profile_event_icon_desc),
        Triple(R.drawable.ic_setting, R.string.profile_setting, R.string.profile_setting_icon_desc),
    )

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        actions.forEachIndexed { index, (iconRes, labelRes, descRes) ->
            if (index > 0) {
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(30.dp)
                        .background(Color(0xFFD9D9D9))
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = stringResource(descRes),
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
                Text(
                    text = stringResource(labelRes),
                    color = Color.Black,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}

@Composable
private fun MemberBenefitSection(email: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.profile_member_benefit),
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.profile_arrow),
                color = Color.Black,
                fontSize = 24.sp
            )
        }
        Text(
            text = email ?: stringResource(R.string.profile_available_count),
            color = Color(0xFF7A7A7A),
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 6.dp)
        )
    }
}

@Composable
private fun FollowingSection(followingUsers: List<ReqResUser>) {
    val pagerState = rememberPagerState(pageCount = { followingUsers.size })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.profile_following),
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.profile_edit_text),
                color = Color(0xFF767676),
                fontSize = 12.sp
            )
        }

        if (followingUsers.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                contentPadding = PaddingValues(end = 16.dp),
                pageSpacing = 6.dp
            ) { page ->
                AsyncImage(
                    model = followingUsers[page].avatar,
                    contentDescription = stringResource(R.string.profile_avatar_desc),
                    modifier = Modifier
                        .size(106.dp)
                        .background(Color(0xFFD9D9D9)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
