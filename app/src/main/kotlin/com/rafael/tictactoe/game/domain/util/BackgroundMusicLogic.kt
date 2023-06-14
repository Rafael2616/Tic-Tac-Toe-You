package com.rafael.tictactoe.game.domain.util

import android.content.*
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.common.services.MusicService


suspend fun BackgroundMusicLogic(settingsRepo: SettingsRepository, context: Context ) {

    val settings = settingsRepo.getAllSettingsValues()
    var isBackgroundMusicOn = settings.isBackgroundMusicOn

	if (isBackgroundMusicOn) {
		val serviceIntent = Intent(context, MusicService::class.java)
        context.startService(serviceIntent)
        isBackgroundMusicOn = false
        settingsRepo.setAllSettingsValues(settings)
    } else {
        val serviceIntent = Intent(context, MusicService::class.java)
        context.stopService(serviceIntent)
        isBackgroundMusicOn = true
        settingsRepo.setAllSettingsValues(settings)
	}
}