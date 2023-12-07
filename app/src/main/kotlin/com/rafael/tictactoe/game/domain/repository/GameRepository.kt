package com.rafael.tictactoe.game.domain.repository

import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.domain.model.OnlineGameState
import com.rafael.tictactoe.game.domain.model.Player
import com.rafael.tictactoe.game.domain.model.RequestState
import com.rafael.tictactoe.settings.domain.model.SettingsState
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    // Local data from Room
    fun getGameValues(): Flow<GameEntity>
    suspend fun setGameValues(gameEntity: GameEntity)

    // Internet data by Retrofit
    suspend fun getRemotePlayers(): Pair<Flow<List<Player>>?, RequestState>
    suspend fun postPlayer(state: GameState, settingsState: SettingsState): Pair<Player?, RequestState>
    suspend fun removePlayer(playerID: Int): RequestState

    suspend fun postInitialGameState(matchID: Int): RequestState
    suspend fun getGameState(): Pair<Flow<OnlineGameState>?, RequestState>
    suspend fun updateGameState(state: GameState): Pair<Flow<OnlineGameState>?, RequestState>
    suspend fun endMatch(matchID: Int): RequestState
}
