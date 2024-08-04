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
    val isServiceRunning = runningServiceVerifier(context, MusicService::class.java)
    val serviceIntent = Intent(context, MusicService::class.java)

    if (isBackgroundMusicOn && !isServiceRunning) {
        context.startService(serviceIntent).also {
            Log.i("MusicService", "Starting service ${MusicService::class.java.name}")
        }
        return
    }

    if (isAppInOnStop && isServiceRunning || !isBackgroundMusicOn && isServiceRunning) {
        context.stopService(serviceIntent).also {
            Log.i("MusicService", "Stopping service ${MusicService::class.java.name}")
        }
        return
    }
}
