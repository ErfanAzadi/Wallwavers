package com.example.wallwavers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.wallwavers.navigation.NavGraph
import com.example.wallwavers.navigation.navItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen()

        setContent {
            FixIconColor()
            Box(modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background))) {
                MainScreen()
            }
        }
    }

    @Composable
    private fun MainScreen() {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.systemBarsPadding(),
            bottomBar = { BottomNavigationBar(navController) },
            content = { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    NavGraph(navController = navController)
                }
            },
            backgroundColor = colorResource(R.color.background)
        )
    }

    @Composable
    fun BottomNavigationBar(navController: NavController) {
        BottomNavigation(
            backgroundColor = colorResource(id = R.color.background),
            contentColor = colorResource(id = R.color.content)
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            navItems.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(painterResource(id = item.iconId), contentDescription = null)},
                    selectedContentColor = colorResource(id = R.color.content),
                    unselectedContentColor = colorResource(id = R.color.content).copy(0.4f),
                    alwaysShowLabel = false,
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

    @Composable
    private fun FixIconColor() {
        val isSystemInDarkMode = isSystemInDarkTheme()
        val systemUiColor = rememberSystemUiController()
        SideEffect {
            systemUiColor.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = !isSystemInDarkMode
            )
        }
    }
}