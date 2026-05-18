package com.umc.workbook

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.umc.workbook.ui.bag.BagScreen
import com.umc.workbook.ui.home.HomeScreen
import com.umc.workbook.ui.home.HomeViewModel
import com.umc.workbook.ui.profile.ProfileScreen
import com.umc.workbook.ui.profile.ProfileViewModel
import com.umc.workbook.ui.purchase.PurchaseScreen
import com.umc.workbook.ui.purchase.PurchaseViewModel
import com.umc.workbook.ui.wish.WishlistScreen
import com.umc.workbook.ui.wish.WishlistViewModel

private data class NavItem(val screen: Screen, val iconRes: Int, val labelRes: Int)

private val navItems = listOf(
    NavItem(Screen.Home, R.drawable.ic_house, R.string.nav_home),
    NavItem(Screen.Purchase, R.drawable.ic_search, R.string.nav_purchase),
    NavItem(Screen.Wishlist, R.drawable.ic_heart, R.string.nav_wishlist),
    NavItem(Screen.Bag, R.drawable.ic_bag, R.string.nav_bag),
    NavItem(Screen.Profile, R.drawable.ic_user, R.string.nav_profile),
)

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: Screen.Home.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        bottomBar = {
            BottomNavBar(
                currentRoute = currentRoute,
                onNavigate = { screen ->
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(Screen.Home.route) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                val viewModel: HomeViewModel = viewModel()
                val shoes by viewModel.shoes.collectAsState()
                HomeScreen(shoes = shoes)
            }
            composable(Screen.Purchase.route) {
                val viewModel: PurchaseViewModel = viewModel()
                val items by viewModel.purchaseItems.collectAsState()
                PurchaseScreen(purchaseItems = items, onWishClick = viewModel::toggleWish)
            }
            composable(Screen.Wishlist.route) {
                val viewModel: WishlistViewModel = viewModel()
                val items by viewModel.wishlistItems.collectAsState()
                WishlistScreen(wishlistItems = items)
            }
            composable(Screen.Bag.route) {
                BagScreen(
                    onOrderClick = {
                        navController.navigate(Screen.Purchase.route) {
                            popUpTo(Screen.Home.route) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
            composable(Screen.Profile.route) {
                val viewModel: ProfileViewModel = viewModel()
                val profileUser by viewModel.profileUser.collectAsState()
                val followingUsers by viewModel.followingUsers.collectAsState()
                ProfileScreen(profileUser = profileUser, followingUsers = followingUsers)
            }
        }
    }
}

@Composable
private fun BottomNavBar(
    currentRoute: String,
    onNavigate: (Screen) -> Unit
) {
    HorizontalDivider(color = Color(0xFFEDEDED), thickness = 1.dp)
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp,
        modifier = Modifier.padding(top = 12.dp, bottom = 10.dp)
    ) {
        navItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = { onNavigate(item.screen) },
                icon = {
                    Icon(
                        painter = painterResource(item.iconRes),
                        contentDescription = stringResource(item.labelRes),
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = { Text(text = stringResource(item.labelRes)) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    unselectedIconColor = Color(0xFF7A7A7A),
                    selectedTextColor = Color.Black,
                    unselectedTextColor = Color(0xFF7A7A7A),
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
