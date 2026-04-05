package com.dayat0009.burnoutguard.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dayat0009.burnoutguard.ui.screen.AboutScreen
import com.dayat0009.burnoutguard.ui.screen.MainScreen


@Composable
fun SetupNavGraph (navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable (route = Screen.Home.route){
            MainScreen(navController)
        }
        composable (route = Screen.About.route){
            AboutScreen(navController)
        }


    }
}
