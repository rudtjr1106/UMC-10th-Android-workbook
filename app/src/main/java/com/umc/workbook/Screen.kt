package com.umc.workbook

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Purchase : Screen("purchase")
    object Wishlist : Screen("wishlist")
    object Bag : Screen("bag")
    object Profile : Screen("profile")
}
