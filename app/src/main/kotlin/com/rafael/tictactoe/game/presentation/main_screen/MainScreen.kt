package com.rafael.tictactoe.game.presentation.main_screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.rafael.tictactoe.game.presentation.main_screen.components.MainOptions
import com.rafael.tictactoe.game.presentation.main_screen.components.MainTopBar

@Composable
@ExperimentalMaterial3Api
fun MainScreen(navController: NavController) {

    MainTopBar()
    MainOptions(navController)
}