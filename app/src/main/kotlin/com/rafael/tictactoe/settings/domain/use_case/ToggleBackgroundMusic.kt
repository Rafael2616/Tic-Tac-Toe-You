package com.rafael.tictactoe.settings.domain.use_case

import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.domain.model.SettingsEntity


class ToggleBackgroundMusic(
private val repository: SettingsRepository
) {
suspend operator fun invoke(isBackgroundMusicOn: Boolean) {
  repository.setIsBackgroundMusicOnValue(isBackgroundMusicOn)
  }
}