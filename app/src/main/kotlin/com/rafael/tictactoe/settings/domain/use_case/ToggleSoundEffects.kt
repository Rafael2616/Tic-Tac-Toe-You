package com.rafael.tictactoe.settings.domain.use_case

import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.domain.model.SettingsEntity


class ToggleSoundEffects(
private val repository: SettingsRepository
) {
suspend operator fun invoke(isSoundEffectsOn: Boolean) {
  repository.setIsSoundEffectsOnValue(isSoundEffectsOn)
  }
}