package com.rafael.tictactoe.feature.game.domain.repository

import com.rafael.tictactoe.feature.game.domain.model.GameState
import com.rafael.tictactoe.feature.game.domain.model.OnlineGameState
import com.rafael.tictactoe.feature.game.domain.model.Player
import com.rafael.tictactoe.feature.game.domain.model.RequestState
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import kotlinx.coroutines.flow.Flow

internal interface GameRepository {

    // Internet data by Retrofit
    suspend fun getRemotePlayers(): Pair<List<Player>?, RequestState>
    suspend fun postPlayer(state: GameState, settingsState: SettingsState): RequestState
    suspend fun removePlayer(playerID: Int): RequestState

    suspend fun postInitialGameState(matchID: Int): RequestState
    suspend fun getGameState(): Pair<Flow<OnlineGameState>?, RequestState>
    suspend fun updateGameState(state: GameState): Pair<Flow<OnlineGameState>?, RequestState>
    suspend fun endMatch(matchID: Int): RequestState
}
