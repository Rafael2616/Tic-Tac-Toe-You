package com.rafael.tictactoe.common.presentation.navigation

sealed class Screen(val route: String) {
    object Splash: Screen(route = "splash")
    object Main: Screen(route = "main")
    object Game: Screen(route = "game")
    object Settings: Screen(route = "settings")
}