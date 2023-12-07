package com.rafael.tictactoe.game.domain.audio

import android.content.Context
import android.content.Intent
import com.rafael.tictactoe.common.domain.util.runningServiceVerifier
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.settings.domain.model.SettingsEvent

fun backgroundMusic(
    service: MusicService,
    context: Context,
    isBMPlaying: Boolean,
    isBackgroundMusicOn: Boolean,
    onEvent: (SettingsEvent) -> Unit,
    isAppInOnStop: Boolean = false,
) {
    val isServiceRunning = ::runningServiceVerifier
    val serviceIntent = Intent(context, service::class.java)

    if (!isAppInOnStop) {
        if (isBackgroundMusicOn && !isServiceRunning(context, MusicService::class.java)) {
            if (!isBMPlaying) {
                context.startService(serviceIntent)
                onEvent(SettingsEvent.IsBGMusicPlaying(true))
            }
        } else {
            context.stopService(serviceIntent)
            onEvent(SettingsEvent.IsBGMusicPlaying(false))
        }
    } else {
        context.stopService(serviceIntent)
        onEvent(SettingsEvent.IsBGMusicPlaying(false))
    }
}
