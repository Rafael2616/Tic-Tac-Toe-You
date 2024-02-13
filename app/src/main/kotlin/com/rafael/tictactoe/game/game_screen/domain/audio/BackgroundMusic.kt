package com.rafael.tictactoe.game.domain.audio

import android.content.Context
import android.content.Intent
import com.rafael.tictactoe.common.domain.util.runningServiceVerifier
import com.rafael.tictactoe.common.services.MusicService

fun backgroundMusic(
    service: MusicService,
    context: Context,
    isBMPlaying: Boolean,
    isBackgroundMusicOn: Boolean,
    isAppInOnStop: Boolean = false,
): Boolean? {
    val isServiceRunning = ::runningServiceVerifier
    val serviceIntent = Intent(context, service::class.java)
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
