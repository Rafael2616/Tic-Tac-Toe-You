package com.rafael.tictactoe.core.navigation.impl.utils

import android.app.Application
import android.content.Context
import android.content.Intent
import com.rafael.tictactoe.core.navigation.Destinations
import com.rafael.tictactoe.core.navigation.Game
import com.rafael.tictactoe.core.navigation.GameMode
import com.rafael.tictactoe.core.resources.R
import org.koin.java.KoinJavaComponent.inject

fun navigateToShortcutsDestination(intent: Intent): Destinations? {
    val context: Context by inject(Application::class.java)
    if (intent.getStringExtra(context.getString(R.string.play)) == "s2") {
        return Game(GameMode.PvP)
    }
    if (intent.getStringExtra(context.getString(R.string.vs_cpu)) == "s1") {
        return Game(GameMode.VsCPU)
    }
    if (intent.getStringExtra(context.getString(R.string.online_match)) == "s0") {
        return Game(GameMode.Online)
    }
    return null
}
