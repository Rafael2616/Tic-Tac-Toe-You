package com.rafael.tictactoe.feature.game.domain.audio

import com.rafael.tictactoe.core.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.game.domain.model.GameState

expect fun gotOldSound(state: GameState, settingsState: SettingsState): Boolean
