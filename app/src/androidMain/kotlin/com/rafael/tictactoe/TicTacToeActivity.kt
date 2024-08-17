package com.rafael.tictactoe

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.rafael.tictactoe.core.game.domain.model.GameMode
import com.rafael.tictactoe.core.navigation.Destinations
import com.rafael.tictactoe.core.navigation.Game
import com.rafael.tictactoe.core.navigation.impl.TicTacToeNavGraph
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.exception.AppExceptionHandler
import com.rafael.tictactoe.shortcuts.GameShortcutsCreator

class TicTacToeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        AppExceptionHandler.init()
        GameShortcutsCreator().create(this)

        val destination = handleIntent(intent)
        setContent {
            TicTacToeNavGraph(destination = destination)
        }
    }

    private fun handleIntent(intent: Intent): Destinations? {
        if (intent.getStringExtra(getString(R.string.play)) == "s2") {
            return Game(GameMode.PvP)
        }
        if (intent.getStringExtra(getString(R.string.vs_cpu)) == "s1") {
            return Game(GameMode.VsCPU)
        }
        if (intent.getStringExtra(getString(R.string.online_match)) == "s0") {
            return Game(GameMode.Online)
        }
        return null
    }
}
