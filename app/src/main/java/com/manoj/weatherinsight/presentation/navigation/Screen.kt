package com.manoj.weatherinsight.presentation.navigation

/**
 * Sealed class representing all possible screens in the application.
 * Each object holds a unique [route] string used for Jetpack Compose Navigation.
 *
 * @author Manojkumar
 */
sealed class Screen(val route: String) {

    /** The main landing screen showing weather details. */
    data object Home : Screen("home")

    /** The screen for searching and managing locations (if applicable). */
    data object Search : Screen("search")
}
