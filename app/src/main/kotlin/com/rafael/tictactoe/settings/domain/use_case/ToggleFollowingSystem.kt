package com.rafael.tictactoe.settings.domain.use_case

import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.domain.model.SettingsEntity


class ToggleFollowingSystem(
private val repository: SettingsRepository
) {
suspend operator fun invoke(isFollowingSystemOn: Boolean) {
  repository.setIsFollowingSystemOn(isFollowingSystemOn)
  }
}