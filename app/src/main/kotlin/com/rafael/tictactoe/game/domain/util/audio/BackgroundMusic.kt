package com.rafael.tictactoe.game.domain.util

import android.content.Context
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.common.domain.util.ServiceRunningVerifier
import com.rafael.tictactoe.common.domain.util.AppForegroundNotifier

fun BackgroundMusic(
    context: Context,
    isBackgroundMusicOn: Boolean
) {

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