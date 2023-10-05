package com.example.wallwavers.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wallwavers.feature_home.presentation.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavItem.Home.route) {
        composable(NavItem.Home.route) {
            HomeScreen()
        }
        composable(NavItem.Favorites.route) {

        }
        composable(NavItem.Account.route) {

        }
    }
}