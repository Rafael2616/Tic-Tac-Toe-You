package com.rafael.tictactoe.game.domain.repository

import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.domain.model.GameEntity


interface GameRepository {

    suspend fun getAllGameValues(): GameEntity

    suspend fun setAllGameValues(gameEntity: GameEntity)

    suspend fun setPlayer1Name(player1Name: String)

    suspend fun setPlayer2Name(player2Name: String)

    suspend fun setPlayer1Wins(player1Wins: Int)

    suspend fun setPlayer2Wins(player2Wins: Int)
/*
    suspend fun setCards(cards: List<Boolean>)

    suspend fun setIsCardClicked(isCardClicked: List<Boolean>)
*/
    suspend fun setIsScoreUp(isScoreUp: Boolean)

    suspend fun setIsXImage(isXImage: Boolean)

    suspend fun setIs1Win(is1Win: Boolean)

    suspend fun setIsXFor1(isXFor1: Boolean)

    suspend fun setIsPlayer1(isPlayer1: Boolean)

    suspend fun setPlayer1InToast(player1InToast: Boolean)

    suspend fun setPlay(play: Boolean)

    suspend fun setExpandButton(expandButton: Boolean)

    suspend fun setIsWinSoundPlaying(isWinSoundPlaying: Boolean)

    suspend fun setIsGotOldSoundPlaying(isGotOldSoundPlaying: Boolean)

    suspend fun setIsSoundEffectsOn(isSoundEffectsOn: Boolean)

    suspend fun setIsBackgroundMusicOn(isBackgroundMusicOn: Boolean)

    suspend fun setCoordinates(startX: Float, startY: Float, endX: Float, endY: Float)

    suspend fun setWinner(winner: Boolean)

    suspend fun setGotOld(gotOld: Boolean)

    suspend fun setCurrentPlayerName(currentPlayerName: String)




    suspend fun getPlayer1Name(): String

    suspend fun getPlayer2Name(): String

    suspend fun getPlayer1Wins(): Int

    suspend fun getPlayer2Wins(): Int
/*
    suspend fun getCards(): List<Boolean>

    suspend fun getIsCardClicked(): List<Boolean>
*/
    suspend fun getIsScoreUp(): Boolean

    suspend fun getIsXImage(): Boolean

    suspend fun getIs1Win(): Boolean

    suspend fun getIsXFor1(): Boolean

    suspend fun getIsPlayer1(): Boolean

    suspend fun getPlayer1InToast(): Boolean

    suspend fun getPlay(): Boolean

    suspend fun getExpandButton(): Boolean

    suspend fun getIsWinSoundPlaying(): Boolean

    suspend fun getIsGotOldSoundPlaying(): Boolean

    suspend fun getIsSoundEffectsOn(): Boolean

    suspend fun getIsBackgroundMusicOn(): Boolean

    suspend fun getStartX(): Float

    suspend fun getStartY(): Float

    suspend fun getEndX(): Float

    suspend fun getEndY(): Float

    suspend fun getWinner(): Boolean

    suspend fun getGotOld(): Boolean

    suspend fun getCurrentPlayerName(): String

}