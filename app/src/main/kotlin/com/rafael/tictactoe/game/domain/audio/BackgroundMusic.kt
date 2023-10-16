package com.rafael.tictactoe.game.domain.audio

import android.content.Context
import android.content.Intent
import com.rafael.tictactoe.common.domain.util.RunningServiceVerifier
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun BackgroundMusic(
    context: Context,
    viewModel: SettingsViewModel,
    isAppInOnStop: Boolean = false
) {
	val isServiceRunning = ::RunningServiceVerifier
    val serviceIntent = Intent(context, MusicService::class.java)
    val isBMPlaying = viewModel.isBMPlaying.value
    val isBackgroundMusicOn = viewModel.isBackgroundMusicOn.value

    if (!isAppInOnStop) {
	    if (isBackgroundMusicOn && !isServiceRunning(context, MusicService::class.java)) {
            if  (!isBMPlaying) {
            	context.startService(serviceIntent)
                viewModel.updateIsBMPlaying(true)
            }
    	} else {
   	     context.stopService(serviceIntent)
            viewModel.updateIsBMPlaying(false)
        }
    } else {
   	 context.stopService(serviceIntent)
        viewModel.updateIsBMPlaying(false)
	}
}