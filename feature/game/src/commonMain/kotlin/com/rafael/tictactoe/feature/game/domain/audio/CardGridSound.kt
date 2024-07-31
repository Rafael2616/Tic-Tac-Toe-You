package com.rafael.tictactoe.feature.game.domain.audio

import com.rafael.tictactoe.feature.game.domain.model.GameState
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState

expect fun cardGridSound(state: GameState, settingsState: SettingsState)
