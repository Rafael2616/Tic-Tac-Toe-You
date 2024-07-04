package com.rafael.tictactoe.core.services.music

import android.content.Context
import android.content.Intent
import com.rafael.tictactoe.core.services.util.runningServiceVerifier

fun backgroundMusic(
    context: Context,
    isBMPlaying: Boolean,
    isBackgroundMusicOn: Boolean,
    isAppInOnStop: Boolean = false,
): Boolean? {
    val isServiceRunning = ::runningServiceVerifier
    val serviceIntent = Intent(context, MusicService::class.java)
    var isBGMusicPlaying: Boolean? = null

    if (!isAppInOnStop) {
        if (isBackgroundMusicOn && !isServiceRunning(context, MusicService::class.java)) {
            if (!isBMPlaying) {
                context.startService(serviceIntent)
                isBGMusicPlaying = true
            }
        } else {
            context.stopService(serviceIntent)
            isBGMusicPlaying = false
        }
    } else {
        context.stopService(serviceIntent)
        isBGMusicPlaying = false
    }
    return isBGMusicPlaying
}
