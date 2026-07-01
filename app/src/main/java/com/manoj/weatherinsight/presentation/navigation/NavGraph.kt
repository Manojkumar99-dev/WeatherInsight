package com.manoj.weatherinsight.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.manoj.weatherinsight.presentation.home.HomeScreen
import com.manoj.weatherinsight.presentation.search.SearchScreen

/**
 * Defines the navigation structure of the app.
 * Uses Jetpack Compose Navigation to manage transitions between [HomeScreen] and [SearchScreen].
 *
 * @param navController The [NavHostController] responsible for navigating between composables.
 * @author Manojkumar
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        // Navigation route for the Home screen.
        composable(Screen.Home.route) {

            HomeScreen(
                snackbarHostState = snackbarHostState
            )
        }

        // Navigation route for the Search screen.
        composable(Screen.Search.route) {
            SearchScreen()
        }
    }
}
