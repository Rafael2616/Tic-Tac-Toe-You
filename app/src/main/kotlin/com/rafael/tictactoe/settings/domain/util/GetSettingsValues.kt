package com.rafael.tictactoe.settings.domain.util

import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.domain.model.SettingsEntity

class GetSettingsValues(
private val repository: SettingsRepository
) {
suspend operator fun invoke(): SettingsEntity {
   return repository.getSettingsEntity()
  }
}