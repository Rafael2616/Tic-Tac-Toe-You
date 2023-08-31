package com.rafael.tictactoe.common.presentation.navigation

sealed class Destinations(val screen: String) {
    object Splash: Destinations(screen = "splash")
    object Main: Destinations(screen = "main")
    object Game: Destinations(screen = "game")
    object Settings: Destinations(screen = "settings")
}