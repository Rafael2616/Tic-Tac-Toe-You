package com.rafael.tictactoe.settings.domain.use_case

import com.rafael.tictactoe.settings.domain.use_case.ToggleDarkTheme
import com.rafael.tictactoe.settings.domain.use_case.ToggleDynamicColors
import com.rafael.tictactoe.settings.domain.use_case.ToggleBackgroundMusic
import com.rafael.tictactoe.settings.domain.use_case.ToggleSoundEffects
import com.rafael.tictactoe.settings.domain.use_case.ToggleFollowingSystem
import com.rafael.tictactoe.settings.domain.use_case.ToggleAllSettingsValues


data class SettingsUseCases(
val toggleDarkTheme: ToggleDarkTheme,
val toggleDynamicColors: ToggleDynamicColors,
val toggleBackgroundMusic: ToggleBackgroundMusic,
val toggleSoundEffects: ToggleSoundEffects,
val toggleFollowingSystem: ToggleFollowingSystem,
val toggleAllSettingsValues: ToggleAllSettingsValues
)