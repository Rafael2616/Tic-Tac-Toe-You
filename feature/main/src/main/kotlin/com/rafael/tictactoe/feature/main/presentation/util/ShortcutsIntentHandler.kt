package com.rafael.tictactoe.feature.main.presentation.util

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.rafael.tictactoe.core.navigation.Destinations
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.feature.main.domain.model.GameMode
import com.rafael.tictactoe.feature.main.domain.model.MainEvent

@Composable
fun ShortcutsIntentHandler(
    context: ComponentActivity,
    navController: NavController,
    onEvent: (MainEvent) -> Unit,
    intent: Intent,
) {
    if (intent.getStringExtra(context.getString(R.string.play)) == "s2") {
        navController.navigate(Destinations.Game.screen)
        onEvent(MainEvent.SetGameMode(GameMode.PvP))
    }
    if (intent.getStringExtra(context.getString(R.string.vs_cpu)) == "s1") {
        navController.navigate(Destinations.Game.screen)
        onEvent(MainEvent.SetGameMode(GameMode.VsCPU))
    }
    if (intent.getStringExtra(context.getString(R.string.online_match)) == "s0") {
        navController.navigate(Destinations.Game.screen)
        onEvent(MainEvent.SetGameMode(GameMode.Online))
    }
}
