package com.rafael.tictactoe.settings.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class SettingsEntity(
  @PrimaryKey
  var isBackgroundMusicOn: Boolean,
  var isDarkThemeOn: Boolean,
  var isDynamicColorsOn: Boolean,
  var isFollowingSystemOn: Boolean,
  var isSoundEffectsOn: Boolean
)