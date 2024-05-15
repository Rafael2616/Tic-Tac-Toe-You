package com.rafael.tictactoe.feature.main.presentation.util

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.rafael.tictactoe.core.navigation.destinations.Game
import com.rafael.tictactoe.core.resources.R

@Composable
fun ShortcutsIntentHandler(
    context: ComponentActivity,
    navController: NavController,
    intent: Intent,
) {
    if (intent.getStringExtra(context.getString(R.string.play)) == "s2") {
        navController.navigate(Game("PvP"))
    }
    if (intent.getStringExtra(context.getString(R.string.vs_cpu)) == "s1") {
        navController.navigate(Game("VsCPU"))
    }
    if (intent.getStringExtra(context.getString(R.string.online_match)) == "s0") {
        navController.navigate(Game("Online"))
    }
}
