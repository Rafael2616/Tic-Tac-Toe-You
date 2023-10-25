package com.rafael.tictactoe.game.presentation.splash_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.common.presentation.navigation.Destinations
import com.rafael.tictactoe.game.presentation.splash_screen.components.SplashImage
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val settingsState by settingsVM.state.collectAsState()

    SplashImage(settingsState)
    
    LaunchedEffect(Unit) {
        delay(900)
        navController.popBackStack()
        navController.navigate(Destinations.Main.screen)
    }
}