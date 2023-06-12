package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel


suspend fun StartSoundEffects(settingsRepo: SettingsRepository) {

	val isSoundEffectsOn = settingsRepo.getIsSoundEffectsOn()
	settingsRepo.setIsSoundEffectsOn(!isSoundEffectsOn)
}