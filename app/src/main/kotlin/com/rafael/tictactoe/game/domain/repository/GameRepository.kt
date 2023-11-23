package com.rafael.tictactoe.game.domain.repository

import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.model.Player
import com.rafael.tictactoe.game.domain.model.RequestState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface GameRepository {

    //Local data from Room
    fun getGameValues(): Flow<GameEntity>
    suspend fun setGameValues(gameEntity: GameEntity)
    
   //Internet data by Retrofit
    suspend fun getRemotePlayers(): Pair<Flow<List<Player>>?, RequestState>

}