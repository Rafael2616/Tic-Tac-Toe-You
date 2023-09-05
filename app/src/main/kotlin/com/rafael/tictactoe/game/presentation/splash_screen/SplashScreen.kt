package com.rafael.tictactoe.game.presentation.splash_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.rafael.tictactoe.common.presentation.navigation.Destinations
import com.rafael.tictactoe.game.presentation.splash_screen.components.SplashImage
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    SplashImage()
    
    LaunchedEffect(Unit) {
        delay(1200)
        navController.popBackStack()
        navController.navigate(Destinations.Main.screen)
    }
}