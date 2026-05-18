package com.umc.workbook.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.umc.workbook.ui.bag.BagScreen
import com.umc.workbook.ui.home.HomeScreen
import com.umc.workbook.ui.profile.ProfileScreen
import com.umc.workbook.ui.purchase.PurchaseScreen
import com.umc.workbook.ui.theme.Black
import com.umc.workbook.ui.theme.Gray000
import com.umc.workbook.ui.theme.Gray100
import com.umc.workbook.ui.theme.White
import com.umc.workbook.ui.wish.WishlistScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            Column {
                HorizontalDivider(color = Gray000, thickness = 1.dp)
                NavigationBar(
                    containerColor = White,
                    tonalElevation = 0.dp
                ) {
                    BottomNavItem.entries.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(item.iconResId),
                                    contentDescription = stringResource(item.labelResId),
                                    modifier = Modifier.padding(0.dp)
                                )
                            },
                            label = { Text(stringResource(item.labelResId)) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Black,
                                selectedTextColor = Black,
                                unselectedIconColor = Gray100,
                                unselectedTextColor = Gray100,
                                indicatorColor = White
                            )
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomNavItem.Home.route) { HomeScreen() }
            composable(BottomNavItem.Purchase.route) { PurchaseScreen() }
            composable(BottomNavItem.Wishlist.route) { WishlistScreen() }
            composable(BottomNavItem.Bag.route) {
                BagScreen(
                    onOrderClick = {
                        navController.navigate(BottomNavItem.Purchase.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
            composable(BottomNavItem.Profile.route) { ProfileScreen() }
        }
    }
}
