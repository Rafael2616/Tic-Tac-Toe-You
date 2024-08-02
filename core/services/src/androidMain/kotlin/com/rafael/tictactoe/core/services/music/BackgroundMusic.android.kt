package com.rafael.tictactoe.core.services.music

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import com.rafael.tictactoe.core.services.util.runningServiceVerifier
import org.koin.java.KoinJavaComponent.inject

actual fun backgroundMusic(
    isAppInOnStop: Boolean,
    isBackgroundMusicOn: Boolean,
) {
    val context: Context by inject(Application::class.java)
    val isServiceRunning = ::runningServiceVerifier
    val serviceIntent = Intent(context, MusicService::class.java)

    if (isAppInOnStop || !isBackgroundMusicOn) {
        context.stopService(serviceIntent).also {
            Log.i("MusicService", "Stopping service ${MusicService::class.java.name}")
        }
        return
    }

    if (!isServiceRunning(context, MusicService::class.java)) {
        context.startService(serviceIntent).also {
            Log.i("MusicService", "Starting service ${MusicService::class.java.name}")
        }
    }
}
