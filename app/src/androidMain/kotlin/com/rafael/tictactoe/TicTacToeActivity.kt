package com.rafael.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.rafael.tictactoe.core.navigation.impl.TicTacToeNavGraph
import com.rafael.tictactoe.core.navigation.impl.utils.navigateToShortcutsDestination
import com.rafael.tictactoe.exception.AppExceptionHandler
import com.rafael.tictactoe.shortcuts.GameShortcutsCreator

class TicTacToeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        AppExceptionHandler.init()
        GameShortcutsCreator().create(this)

        val destination = navigateToShortcutsDestination(intent)
        setContent {
            TicTacToeNavGraph(destination = destination)
        }
    }
}
