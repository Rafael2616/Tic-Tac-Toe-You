package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.settings.domain.repository.SettingsRepository

suspend fun StartSoundEffects(settingsRepo: SettingsRepository) {
    
    val settings = settingsRepo.getAllSettingsValues()
	var isSoundEffectsOn = settings.isSoundEffectsOn
	isSoundEffectsOn = !isSoundEffectsOn
	settingsRepo.setAllSettingsValues(settings)
}