package com.rafael.tictactoe.common.shortcuts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.rafael.tictactoe.R

class ShortcutsReceiver() : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.getStringExtra(context.getString(R.string.play)) == "s0") {
            // navController.navigate(Destinations.Game.screen)
        }
        if (intent.getStringExtra(context.getString(R.string.vs_cpu)) == "s1") {
            // navController.navigate(Destinations.Game.screen)
        }
        if (intent.getStringExtra(context.getString(R.string.online_match)) == "s2") {
            // navController.navigate(Destinations.Game.screen)
        }
        Log.d("ShortcutsReceiver:", " onReceive() triggered")
    }
}
