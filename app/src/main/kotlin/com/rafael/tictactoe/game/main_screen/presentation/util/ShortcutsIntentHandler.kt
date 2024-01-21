package com.rafael.tictactoe.game.mainScreen.presentation.util

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.presentation.navigation.Destinations
import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.mainScreen.domain.model.MainEvent

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
