package com.rafael.tictactoe.feature.game.domain.repository

import com.rafael.tictactoe.core.platform.network.utils.Error
import com.rafael.tictactoe.core.platform.network.utils.Result
import com.rafael.tictactoe.core.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.game.domain.model.GameState
import com.rafael.tictactoe.feature.game.domain.model.OnlineGameState
import com.rafael.tictactoe.feature.game.domain.model.Player
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun getRemotePlayers(): Pair<Result<List<Player>, Error>, ErrorMessage>
    suspend fun postPlayer(
        state: GameState,
        settingsState: SettingsState,
    ): Pair<Result<Unit, Error>, ErrorMessage>

    suspend fun removePlayer(matchID: Int): Pair<Result<Unit, Error>, ErrorMessage>

    suspend fun postInitialGameState(matchID: Int): Pair<Result<Unit, Error>, ErrorMessage>
    suspend fun getGameState(): Pair<Result<Flow<OnlineGameState>, Error>, ErrorMessage>
    suspend fun updateGameState(state: GameState): Pair<Result<Flow<OnlineGameState>, Error>, ErrorMessage>
    suspend fun endMatch(matchID: Int): Pair<Result<Unit, Error>, ErrorMessage>
}

typealias ErrorMessage = String
