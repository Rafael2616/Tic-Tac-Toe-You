package com.rafael.tictactoe.game.data.repository

import com.rafael.tictactoe.game.data.dao.GameDao
import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
private val gameDao: GameDao
) : GameRepository {

    override suspend fun getAllGameValues(): GameEntity {
        return gameDao.getAllGameValues()
    }

    override suspend fun setAllGameValues(gameEntity: GameEntity) {
        gameDao.setAllGameValues(gameEntity)
    }

    override suspend fun setPlayer1Name(player1Name: String) {
        gameDao.setPlayer1Name(player1Name)
    }

    override suspend fun setPlayer2Name(player2Name: String) {
        gameDao.setPlayer2Name(player2Name)
    }

    override suspend fun setPlayer1Wins(player1Wins: Int) {
        gameDao.setPlayer1Wins(player1Wins)
    }

    override suspend fun setPlayer2Wins(player2Wins: Int) {
        gameDao.setPlayer2Wins(player2Wins)
    }
/*
    override suspend fun setCards(cards: List<Boolean>) {
        gameDao.setCards(cards)
    }

    override suspend fun setIsCardClicked(isCardClicked: List<Boolean>) {
        gameDao.setIsCardClicked(isCardClicked)
    }
*/
    override suspend fun setIsScoreUp(isScoreUp: Boolean) {
        gameDao.setIsScoreUp(isScoreUp)
    }

    override suspend fun setIsXImage(isXImage: Boolean) {
        gameDao.setIsXImage(isXImage)
    }

    override suspend fun setIs1Win(is1Win: Boolean) {
        gameDao.setIs1Win(is1Win)
    }

    override suspend fun setIsXFor1(isXFor1: Boolean) {
        gameDao.setIsXFor1(isXFor1)
    }

    override suspend fun setIsPlayer1(isPlayer1: Boolean) {
        gameDao.setIsPlayer1(isPlayer1)
    }

    override suspend fun setPlayer1InToast(player1InToast: Boolean) {
        gameDao.setPlayer1InToast(player1InToast)
    }

    override suspend fun setPlay(play: Boolean) {
        gameDao.setPlay(play)
    }

    override suspend fun setExpandButton(expandButton: Boolean) {
        gameDao.setExpandButton(expandButton)
    }

    override suspend fun setIsWinSoundPlaying(isWinSoundPlaying: Boolean) {
        gameDao.setIsWinSoundPlaying(isWinSoundPlaying)
    }

    override suspend fun setIsGotOldSoundPlaying(isGotOldSoundPlaying: Boolean) {
        gameDao.setIsGotOldSoundPlaying(isGotOldSoundPlaying)
    }

    override suspend fun setIsSoundEffectsOn(isSoundEffectsOn: Boolean) {
        gameDao.setIsSoundEffectsOn(isSoundEffectsOn)
    }

    override suspend fun setIsBackgroundMusicOn(isBackgroundMusicOn: Boolean) {
        gameDao.setIsBackgroundMusicOn(isBackgroundMusicOn)
    }

    override suspend fun setCoordinates(startX: Float, startY: Float, endX: Float, endY: Float) {
        gameDao.setCoordinates(startX, startY, endX, endY)
    }

    override suspend fun setWinner(winner: Boolean) {
        gameDao.setWinner(winner)
    }

    override suspend fun setGotOld(gotOld: Boolean) {
        gameDao.setGotOld(gotOld)
    }
   
     override suspend fun setCurrentPlayerName(currentPlayerName: String) {
        gameDao.setCurrentPlayerName(currentPlayerName)
    }



    override suspend fun getPlayer1Name(): String {
        return gameDao.getPlayer1Name()
    }

    override suspend fun getPlayer2Name(): String {
        return gameDao.getPlayer2Name()
    }

    override suspend fun getPlayer1Wins(): Int {
        return gameDao.getPlayer1Wins()
    }

    override suspend fun getPlayer2Wins(): Int {
        return gameDao.getPlayer2Wins()
    }
/*
    override suspend fun getCards(): List<Boolean> {
        return gameDao.getCards()
    }

    override suspend fun getIsCardClicked(): List<Boolean> {
        return gameDao.getIsCardClicked()
    }
*/
    override suspend fun getIsScoreUp(): Boolean {
        return gameDao.getIsScoreUp()
    }

    override suspend fun getIsXImage(): Boolean {
        return gameDao.getIsXImage()
    }

    override suspend fun getIs1Win(): Boolean {
        return gameDao.getIs1Win()
    }

    override suspend fun getIsXFor1(): Boolean {
        return gameDao.getIsXFor1()
    }

    override suspend fun getIsPlayer1(): Boolean {
        return gameDao.getIsPlayer1()
    }

    override suspend fun getPlayer1InToast(): Boolean {
        return gameDao.getPlayer1InToast()
    }

    override suspend fun getPlay(): Boolean {
        return gameDao.getPlay()
    }

    override suspend fun getExpandButton(): Boolean {
        return gameDao.getExpandButton()
    }

    override suspend fun getIsWinSoundPlaying(): Boolean {
        return gameDao.getIsWinSoundPlaying()
    }

    override suspend fun getIsGotOldSoundPlaying(): Boolean {
        return gameDao.getIsGotOldSoundPlaying()
    }

    override suspend fun getIsSoundEffectsOn(): Boolean {
        return gameDao.getIsSoundEffectsOn()
    }

    override suspend fun getIsBackgroundMusicOn(): Boolean {
        return gameDao.getIsBackgroundMusicOn()
    }

    override suspend fun getStartX(): Float {
        return gameDao.getStartX()
    }

    override suspend fun getStartY(): Float {
        return gameDao.getStartY()
    }

    override suspend fun getEndX(): Float {
        return gameDao.getEndX()
    }

    override suspend fun getEndY(): Float {
        return gameDao.getEndY()
    }
    
    override suspend fun getWinner(): Boolean {
        return gameDao.getWinner()
    }

    override suspend fun getGotOld(): Boolean {
        return gameDao.getGotOld()
    }

    override suspend fun getCurrentPlayerName(): String {
        return gameDao.getCurrentPlayerName()
    }


}