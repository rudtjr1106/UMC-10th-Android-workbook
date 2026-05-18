package com.umc.workbook.ui.navigation

import com.umc.workbook.R

enum class BottomNavItem(
    val route: String,
    val iconResId: Int,
    val labelResId: Int
) {
    Home("home", R.drawable.ic_house, R.string.nav_home),
    Purchase("purchase", R.drawable.ic_search, R.string.nav_purchase),
    Wishlist("wishlist", R.drawable.ic_heart, R.string.nav_wishlist),
    Bag("bag", R.drawable.ic_bag, R.string.nav_bag),
    Profile("profile", R.drawable.ic_user, R.string.nav_profile)
}
