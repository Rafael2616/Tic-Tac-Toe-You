package com.rafael.tictactoe.game.domain.repository

import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.domain.model.GameEntity


interface GameRepository {

    suspend fun getAllGameValues(): GameEntity
    suspend fun setAllGameValues(gameEntity: GameEntity)

    suspend fun getPlayer1Name(): String
    suspend fun setPlayer1Name(player1Name: String)

    suspend fun getPlayer2Name(): String
    suspend fun setPlayer2Name(player2Name: String)

    suspend fun getPlayer1Wins(): Int
    suspend fun setPlayer1Wins(player1Wins: Int)
    
    suspend fun getPlayer2Wins(): Int
    suspend fun setPlayer2Wins(player2Wins: Int)

    suspend fun incrementPlayer1Wins()
    suspend fun incrementPlayer2Wins()

    suspend fun getCards(): List<String>
    suspend fun setCards(cards: List<Boolean>)

    suspend fun getIsCardClicked(): List<String>
    suspend fun setIsCardClicked(isCardClicked: List<Boolean>)

    suspend fun getIsScoreUp(): Boolean
    suspend fun setIsScoreUp(isScoreUp: Boolean)

    suspend fun getIsXImage(): Boolean
    suspend fun setIsXImage(isXImage: Boolean)

    suspend fun getIs1Win(): Boolean
    suspend fun setIs1Win(is1Win: Boolean)

    suspend fun getIsXFor1(): Boolean
    suspend fun setIsXFor1(isXFor1: Boolean)

    suspend fun getIsPlayer1(): Boolean
    suspend fun setIsPlayer1(isPlayer1: Boolean)

    suspend fun getPlayer1InToast(): Boolean
    suspend fun setPlayer1InToast(player1InToast: Boolean)

    suspend fun getPlay(): Boolean
    suspend fun setPlay(play: Boolean)

    suspend fun getExpandButton(): Boolean
    suspend fun setExpandButton(expandButton: Boolean)

    suspend fun getIsWinSoundPlaying(): Boolean
    suspend fun setIsWinSoundPlaying(isWinSoundPlaying: Boolean)

    suspend fun getIsGotOldSoundPlaying(): Boolean
    suspend fun setIsGotOldSoundPlaying(isGotOldSoundPlaying: Boolean)

    suspend fun getWinner(): Boolean
    suspend fun setWinner(winner: Boolean)

    suspend fun getGotOld(): Boolean
    suspend fun setGotOld(gotOld: Boolean)

    suspend fun getCurrentPlayerName(): String
    suspend fun setCurrentPlayerName(currentPlayerName: String)

	suspend fun getShowRestartButton(): Boolean
    suspend fun setShowRestartButton(showRestartButton: Boolean)

    suspend fun getShowPlayer1Name(): String
    suspend fun setShowPlayer1Name(showPlayer1Name: String)

    suspend fun getShowPlayer2Name(): String
    suspend fun setShowPlayer2Name(showPlayer2Name: String)

    suspend fun getStartX(): Float
    suspend fun getStartY(): Float
    suspend fun getEndX(): Float
    suspend fun getEndY(): Float
    suspend fun setCoordinates(startX: Float, startY: Float, endX: Float, endY: Float)

}