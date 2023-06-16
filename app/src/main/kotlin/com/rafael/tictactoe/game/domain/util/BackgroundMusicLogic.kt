package com.rafael.tictactoe.game.domain.util

import android.content.*
import com.rafael.tictactoe.settings.domain.model.SettingsEntity
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.common.services.MusicService

suspend fun BackgroundMusicLogic(settingsRepo: SettingsRepository, context: Context ) {

    val settingsDefaults = SettingsEntity.createDefaultSettings()
    val settings = settingsRepo.getAllSettingsValues() ?: settingsDefaults
    val isBackgroundMusicOn = settings.isBackgroundMusicOn

	if (isBackgroundMusicOn) {
		val serviceIntent = Intent(context, MusicService::class.java)
        context.startService(serviceIntent)
        settings.isBackgroundMusicOn = false
        settingsRepo.setAllSettingsValues(settings)
    } else {
        val serviceIntent = Intent(context, MusicService::class.java)
        context.stopService(serviceIntent)
        settings.isBackgroundMusicOn = true
		settingsRepo.setAllSettingsValues(settings)
	}
}