package com.rafael.tictactoe.core.services.music

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import com.rafael.tictactoe.core.services.util.runningServiceVerifier
import org.koin.java.KoinJavaComponent.inject

actual fun backgroundMusic(
    isBMPlaying: Boolean,
    isBackgroundMusicOn: Boolean,
    isAppInOnStop: Boolean,
): Boolean? {
    val context: Context by inject(Application::class.java)
    val isServiceRunning = ::runningServiceVerifier
    val serviceIntent = Intent(context, MusicService::class.java)
    var isBGMusicPlaying: Boolean? = null

    if (!isAppInOnStop) {
        if (isBackgroundMusicOn && !isServiceRunning(context, MusicService::class.java)) {
            if (!isBMPlaying) {
                context.startService(serviceIntent).also {
                    isBGMusicPlaying = true
                    Log.d("MusicService", "Staring service $it")
                }
            }
        } else {
            context.stopService(serviceIntent).also {
                isBGMusicPlaying = false
                Log.d("MusicService", "Stopping service $it")
            }
        }
    } else {
        context.stopService(serviceIntent)
        isBGMusicPlaying = false
    }
    return isBGMusicPlaying
}
