package com.rafael.tictactoe.game.data.dao

import com.rafael.tictactoe.game.domain.model.OnlineGameState
import com.rafael.tictactoe.game.domain.model.Player
import kotlin.collections.ArrayList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface OnlineGameDao {

    @GET("/data")
    suspend fun getPlayers(): Response<ArrayList<Player>>

    @POST("/postPlayer")
    suspend fun postPlayer(@Body player: Player): Response<Unit>

    @DELETE("/removePlayer/{id}")
    suspend fun removePlayer(@Path("id") playerId: Int): Response<Unit>

    @POST("/postInitialGameState")
    suspend fun postInitialGameState(@Body gameState: OnlineGameState): Response<Unit>

    @GET("/getGameState")
    suspend fun getGameState(): Response<Flow<OnlineGameState>>

    @PUT("/updateGameState")
    suspend fun updateGameState(@Body gameState: OnlineGameState): Response<Flow<OnlineGameState>>

    @DELETE("/endMatch/{id}")
    suspend fun endMatch(@Path("id") matchID: Int): Response<Unit>
}
