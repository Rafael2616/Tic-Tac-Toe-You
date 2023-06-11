package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository


suspend fun StartSoundEffects(repository: GameRepository, settingsRepo: SettingsRepository) {

	val isSoundEffectsOn = settingsRepo.getIsSoundEffectsOn()
	settingsRepo.setIsSoundEffectsOn(!isSoundEffectsOn)
}