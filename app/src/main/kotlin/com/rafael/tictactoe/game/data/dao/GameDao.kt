package com.rafael.tictactoe.game.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import com.rafael.tictactoe.game.domain.model.GameEntity


@Dao
interface GameDao {

@Query("SELECT * FROM GameEntity LIMIT 1")
suspend fun getAllGameValues(): GameEntity

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun setAllGameValues(gameEntity: GameEntity)


@Query("UPDATE GameEntity SET player1Name = :player1Name")
suspend fun setPlayer1Name(player1Name: String)

@Query("UPDATE GameEntity SET player2Name = :player2Name")
suspend fun setPlayer2Name(player2Name: String)

@Query("UPDATE GameEntity SET player1Wins = :player1Wins")
suspend fun setPlayer1Wins(player1Wins: Int)

@Query("UPDATE GameEntity SET player2Wins = :player2Wins")
suspend fun setPlayer2Wins(player2Wins: Int)

@Query("UPDATE GameEntity SET player1Wins = player1Wins + 1")
suspend fun incrementPlayer1Wins()

@Query("UPDATE GameEntity SET player2Wins = player2Wins + 1")
suspend fun incrementPlayer2Wins()

@Query("UPDATE GameEntity SET cards = :cards")
suspend fun setCards(cards: List<Boolean>)

@Query("UPDATE GameEntity SET isCardClicked = :isCardClicked")
suspend fun setIsCardClicked(isCardClicked: List<Boolean>)

@Query("UPDATE GameEntity SET isScoreUp = :isScoreUp")
suspend fun setIsScoreUp(isScoreUp: Boolean)

@Query("UPDATE GameEntity SET isXImage = :isXImage")
suspend fun setIsXImage(isXImage: Boolean)

@Query("UPDATE GameEntity SET is1Win = :is1Win")
suspend fun setIs1Win(is1Win: Boolean)

@Query("UPDATE GameEntity SET isXFor1 = :isXFor1")
suspend fun setIsXFor1(isXFor1: Boolean)

@Query("UPDATE GameEntity SET isPlayer1 = :isPlayer1")
suspend fun setIsPlayer1(isPlayer1: Boolean)

@Query("UPDATE GameEntity SET player1InToast = :player1InToast")
suspend fun setPlayer1InToast(player1InToast: Boolean)

@Query("UPDATE GameEntity SET play = :play")
suspend fun setPlay(play: Boolean)

@Query("UPDATE GameEntity SET expandButton = :expandButton")
suspend fun setExpandButton(expandButton: Boolean)

@Query("UPDATE GameEntity SET isWinSoundPlaying = :isWinSoundPlaying")
suspend fun setIsWinSoundPlaying(isWinSoundPlaying: Boolean)

@Query("UPDATE GameEntity SET isGotOldSoundPlaying = :isGotOldSoundPlaying")
suspend fun setIsGotOldSoundPlaying(isGotOldSoundPlaying: Boolean)

@Query("UPDATE GameEntity SET isSoundEffectsOn = :isSoundEffectsOn")
suspend fun setIsSoundEffectsOn(isSoundEffectsOn: Boolean)

@Query("UPDATE GameEntity SET isBackgroundMusicOn = :isBackgroundMusicOn")
suspend fun setIsBackgroundMusicOn(isBackgroundMusicOn: Boolean)

@Query("UPDATE GameEntity SET startX = :startX, startY = :startY, endX = :endX, endY = :endY")
suspend fun setCoordinates(startX: Float, startY: Float, endX: Float, endY: Float)

@Query("UPDATE GameEntity SET winner = :winner")
suspend fun setWinner(winner: Boolean)

@Query("UPDATE GameEntity SET gotOld = :gotOld")
suspend fun setGotOld(gotOld: Boolean)

@Query("UPDATE GameEntity SET currentPlayerName = :currentPlayerName")
suspend fun setCurrentPlayerName(currentPlayerName: String)



@Query("SELECT player1Name FROM GameEntity LIMIT 1")
suspend fun getPlayer1Name(): String

@Query("SELECT player2Name FROM GameEntity LIMIT 1")
suspend fun getPlayer2Name(): String

@Query("SELECT player1Wins FROM GameEntity LIMIT 1")
suspend fun getPlayer1Wins(): Int

@Query("SELECT player2Wins FROM GameEntity LIMIT 1")
suspend fun getPlayer2Wins(): Int

@Query("SELECT cards FROM GameEntity")
suspend fun getCards(): List<String>

@Query("SELECT isCardClicked FROM GameEntity")
suspend fun getIsCardClicked(): List<String>

@Query("SELECT isScoreUp FROM GameEntity LIMIT 1")
suspend fun getIsScoreUp(): Boolean

@Query("SELECT isXImage FROM GameEntity LIMIT 1")
suspend fun getIsXImage(): Boolean

@Query("SELECT is1Win FROM GameEntity LIMIT 1")
suspend fun getIs1Win(): Boolean

@Query("SELECT isXFor1 FROM GameEntity LIMIT 1")
suspend fun getIsXFor1(): Boolean

@Query("SELECT isPlayer1 FROM GameEntity LIMIT 1")
suspend fun getIsPlayer1(): Boolean

@Query("SELECT player1InToast FROM GameEntity LIMIT 1")
suspend fun getPlayer1InToast(): Boolean

@Query("SELECT play FROM GameEntity LIMIT 1")
suspend fun getPlay(): Boolean

@Query("SELECT expandButton FROM GameEntity LIMIT 1")
suspend fun getExpandButton(): Boolean

@Query("SELECT isWinSoundPlaying FROM GameEntity LIMIT 1")
suspend fun getIsWinSoundPlaying(): Boolean

@Query("SELECT isGotOldSoundPlaying FROM GameEntity LIMIT 1")
suspend fun getIsGotOldSoundPlaying(): Boolean

@Query("SELECT isSoundEffectsOn FROM GameEntity LIMIT 1")
suspend fun getIsSoundEffectsOn(): Boolean

@Query("SELECT isBackgroundMusicOn FROM GameEntity LIMIT 1")
suspend fun getIsBackgroundMusicOn(): Boolean

@Query("SELECT startX FROM GameEntity LIMIT 1")
suspend fun getStartX(): Float

@Query("SELECT startY FROM GameEntity LIMIT 1")
suspend fun getStartY(): Float

@Query("SELECT endX FROM GameEntity LIMIT 1")
suspend fun getEndX(): Float

@Query("SELECT endY FROM GameEntity LIMIT 1")
suspend fun getEndY(): Float

@Query("SELECT winner FROM GameEntity LIMIT 1")
suspend fun getWinner(): Boolean

@Query("SELECT gotOld FROM GameEntity LIMIT 1")
suspend fun getGotOld(): Boolean

@Query("SELECT currentPlayerName FROM GameEntity LIMIT 1")
suspend fun getCurrentPlayerName(): String


@Query("SELECT showRestartButton FROM GameEntity LIMIT 1")
suspend fun getShowRestartButton(): Boolean

@Query("UPDATE GameEntity SET showRestartButton = :showRestartButton")
suspend fun setShowRestartButton(showRestartButton: Boolean)

}