package com.rafael.tictactoe.game.domain.util

import android.content.*
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.common.domain.util.ServiceRunningVerifier

fun BackgroundMusic(isBackgroundMusicOn: Boolean, context: Context ) {
	val isServiceRunning = ::ServiceRunningVerifier
	
	if (isBackgroundMusicOn) {
		if (!isServiceRunning(context, MusicService::class.java)) {
			val serviceIntent = Intent(context, MusicService::class.java)
    		context.startService(serviceIntent)
			}
		// Do Nothing
		} else {
		val serviceIntent = Intent(context, MusicService::class.java)
   	 context.stopService(serviceIntent)
	}
}