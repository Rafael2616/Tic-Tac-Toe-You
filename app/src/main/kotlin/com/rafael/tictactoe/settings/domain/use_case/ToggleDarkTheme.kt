package com.rafael.tictactoe.settings.domain.use_case

import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.domain.model.SettingsEntity


class ToggleDarkTheme(
private val repository: SettingsRepository
) {
suspend operator fun invoke(isDarkThemeOn: Boolean) {
  repository.setIsDarkThemeOn(isDarkThemeOn)
  }
}