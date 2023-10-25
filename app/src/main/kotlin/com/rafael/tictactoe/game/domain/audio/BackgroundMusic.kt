package com.rafael.tictactoe.game.domain.audio

import android.content.Context
import android.content.Intent
import com.rafael.tictactoe.common.domain.util.RunningServiceVerifier
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.domain.model.SettingsState

fun BackgroundMusic(
    context: Context,
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
    isAppInOnStop: Boolean = false
) {
	val isServiceRunning = ::RunningServiceVerifier
    val serviceIntent = Intent(context, MusicService::class.java)
    val isBMPlaying = state.isBMPlaying
    val isBackgroundMusicOn = state.isBackgroundMusicOn

    if (!isAppInOnStop) {
	    if (isBackgroundMusicOn && !isServiceRunning(context, MusicService::class.java)) {
            if  (!isBMPlaying) {
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