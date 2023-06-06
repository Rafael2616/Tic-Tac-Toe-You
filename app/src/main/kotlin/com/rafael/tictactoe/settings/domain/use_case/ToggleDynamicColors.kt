package com.rafael.tictactoe.settings.domain.use_case

import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.domain.model.SettingsEntity


class ToggleDynamicColors(
private val repository: SettingsRepository
) {
suspend operator fun invoke(isDynamicColorsOn: Boolean) {
  repository.setIsDynamicColorsOn(isDynamicColorsOn)
  }
}