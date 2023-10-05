package com.example.wallwavers.navigation

import com.example.wallwavers.R

sealed class NavItem(val route: String, val iconId: Int) {
    object Home: NavItem("home", R.drawable.ic_home)
    object Favorites: NavItem("favorites", R.drawable.ic_fav_filled)
    object Account: NavItem("account", R.drawable.ic_account)
}

val navItems = listOf(
    NavItem.Home,
    NavItem.Favorites,
    NavItem.Account
)