package com.rafael.tictactoe.game.domain.util

import android.content.Context
import android.content.Intent
import com.rafael.tictactoe.common.domain.util.AppForegroundNotifier
import com.rafael.tictactoe.common.domain.util.RunningServiceVerifier
import com.rafael.tictactoe.common.services.MusicService

fun BackgroundMusic(
    context: Context,
    isBackgroundMusicOn: Boolean
) {
	val isServiceRunning = ::RunningServiceVerifier	
		
	if (isBackgroundMusicOn) {
		if (!isServiceRunning(context, MusicService::class.java)) {
			val serviceIntent = Intent(context, MusicService::class.java)
    		context.startService(serviceIntent)
		}
		// Do nothing
	} else {
		val serviceIntent = Intent(context, MusicService::class.java)
   	 context.stopService(serviceIntent)
	}
}