package com.umc.workbook.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.umc.workbook.R
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable data object Home : Screen
    @Serializable data object Purchase : Screen
    @Serializable data object Wishlist : Screen
    @Serializable data object Bag : Screen
    @Serializable data object Profile : Screen
}

data class BottomNavItem(
    val screen: Screen,
    @StringRes val labelRes: Int,
    @DrawableRes val iconRes: Int,
)

val bottomNavItems = listOf(
    BottomNavItem(Screen.Home, R.string.nav_home, R.drawable.ic_house),
    BottomNavItem(Screen.Purchase, R.string.nav_purchase, R.drawable.ic_search),
    BottomNavItem(Screen.Wishlist, R.string.nav_wishlist, R.drawable.ic_heart),
    BottomNavItem(Screen.Bag, R.string.nav_bag, R.drawable.ic_bag),
    BottomNavItem(Screen.Profile, R.string.nav_profile, R.drawable.ic_user),
)
