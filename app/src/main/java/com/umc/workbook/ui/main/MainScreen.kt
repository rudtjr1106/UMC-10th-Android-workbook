package com.umc.workbook.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.umc.workbook.ui.bag.BagScreen
import com.umc.workbook.ui.home.HomeScreen
import com.umc.workbook.ui.navigation.Screen
import com.umc.workbook.ui.navigation.bottomNavItems
import com.umc.workbook.ui.profile.ProfileScreen
import com.umc.workbook.ui.purchase.PurchaseScreen
import com.umc.workbook.ui.wish.WishlistScreen

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            WorkbookBottomBar(
                currentDestination = currentDestination,
                onNavigate = { screen -> navController.navigateToTab(screen) },
            )
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            composable<Screen.Home> { HomeScreen() }
            composable<Screen.Purchase> { PurchaseScreen() }
            composable<Screen.Wishlist> { WishlistScreen() }
            composable<Screen.Bag> {
                BagScreen(
                    onNavigateToPurchase = { navController.navigateToTab(Screen.Purchase) },
                )
            }
            composable<Screen.Profile> { ProfileScreen() }
        }
    }
}

@Composable
private fun WorkbookBottomBar(
    currentDestination: NavDestination?,
    onNavigate: (Screen) -> Unit,
) {
    Column {
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.surfaceVariant,
        )
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
            tonalElevation = 0.dp,
        ) {
            bottomNavItems.forEach { item ->
                val label = stringResource(item.labelRes)
                NavigationBarItem(
                    selected = currentDestination?.hasRoute(item.screen::class) == true,
                    onClick = { onNavigate(item.screen) },
                    icon = {
                        Icon(
                            painter = painterResource(item.iconRes),
                            contentDescription = label,
                            modifier = Modifier.size(30.dp),
                        )
                    },
                    label = { Text(text = label) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        indicatorColor = Color.Transparent,
                    ),
                )
            }
        }
    }
}

private fun NavHostController.navigateToTab(screen: Screen) {
    navigate(screen) {
        popUpTo<Screen.Home> { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
