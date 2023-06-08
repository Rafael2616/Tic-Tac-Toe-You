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
        return gameDao.setAllGameValues(gameEntity)
    }

    override suspend fun getPlayer1Name(): String {
        return gameDao.getPlayer1Name()
    }
    override suspend fun setPlayer1Name(player1Name: String) {
        return gameDao.setPlayer1Name(player1Name)
    }

    override suspend fun getPlayer2Name(): String {
        return gameDao.getPlayer2Name()
    }
    override suspend fun setPlayer2Name(player2Name: String) {
        return gameDao.setPlayer2Name(player2Name)
    }

    override suspend fun getPlayer1Wins(): Int {
        return gameDao.getPlayer1Wins()
    }
    override suspend fun setPlayer1Wins(player1Wins: Int) {
        return gameDao.setPlayer1Wins(player1Wins)
    }

    override suspend fun incrementPlayer1Wins() {
        return gameDao.incrementPlayer1Wins()
    }
    override suspend fun incrementPlayer2Wins() {
        return gameDao.incrementPlayer2Wins()
    }

    override suspend fun getPlayer2Wins(): Int {
        return gameDao.getPlayer2Wins()
    }
    override suspend fun setPlayer2Wins(player2Wins: Int) {
        return gameDao.setPlayer2Wins(player2Wins)
    }

    override suspend fun getCards(): List<String> {
        return gameDao.getCards()
    }
    override suspend fun setCards(cards: List<Boolean>) {
        return gameDao.setCards(cards)
    }

    override suspend fun getIsCardClicked(): List<String> {
        return gameDao.getIsCardClicked()
    }
    override suspend fun setIsCardClicked(isCardClicked: List<Boolean>) {
        return gameDao.setIsCardClicked(isCardClicked)
    }

    override suspend fun getIsScoreUp(): Boolean {
        return gameDao.getIsScoreUp()
    }
    override suspend fun setIsScoreUp(isScoreUp: Boolean) {
        return gameDao.setIsScoreUp(isScoreUp)
    }

    override suspend fun getIsXImage(): Boolean {
        return gameDao.getIsXImage()
    }
    override suspend fun setIsXImage(isXImage: Boolean) {
        return gameDao.setIsXImage(isXImage)
    }

    override suspend fun getIs1Win(): Boolean {
        return gameDao.getIs1Win()
    }
    override suspend fun setIs1Win(is1Win: Boolean) {
        return gameDao.setIs1Win(is1Win)
    }

    override suspend fun getIsXFor1(): Boolean {
        return gameDao.getIsXFor1()
    }
    override suspend fun setIsXFor1(isXFor1: Boolean) {
        return gameDao.setIsXFor1(isXFor1)
    }

    override suspend fun getIsPlayer1(): Boolean {
        return gameDao.getIsPlayer1()
    }
    override suspend fun setIsPlayer1(isPlayer1: Boolean) {
        return gameDao.setIsPlayer1(isPlayer1)
    }

    override suspend fun getPlayer1InToast(): Boolean {
        return gameDao.getPlayer1InToast()
    }
    override suspend fun setPlayer1InToast(player1InToast: Boolean) {
        return gameDao.setPlayer1InToast(player1InToast)
    }

    override suspend fun getPlay(): Boolean {
        return gameDao.getPlay()
    }
    override suspend fun setPlay(play: Boolean) {
        return gameDao.setPlay(play)
    }

    override suspend fun getExpandButton(): Boolean {
        return gameDao.getExpandButton()
    }
    override suspend fun setExpandButton(expandButton: Boolean) {
        return gameDao.setExpandButton(expandButton)
    }

    override suspend fun getIsWinSoundPlaying(): Boolean {
        return gameDao.getIsWinSoundPlaying()
    }
    override suspend fun setIsWinSoundPlaying(isWinSoundPlaying: Boolean) {
        return gameDao.setIsWinSoundPlaying(isWinSoundPlaying)
    }

    override suspend fun getIsGotOldSoundPlaying(): Boolean {
        return gameDao.getIsGotOldSoundPlaying()
    }
    override suspend fun setIsGotOldSoundPlaying(isGotOldSoundPlaying: Boolean) {
        return gameDao.setIsGotOldSoundPlaying(isGotOldSoundPlaying)
    }

    override suspend fun getWinner(): Boolean {
        return gameDao.getWinner()
    }
    override suspend fun setWinner(winner: Boolean) {
        return gameDao.setWinner(winner)
    }

    override suspend fun getGotOld(): Boolean {
        return gameDao.getGotOld()
    }
    override suspend fun setGotOld(gotOld: Boolean) {
        return gameDao.setGotOld(gotOld)
    }
   
    override suspend fun getCurrentPlayerName(): String {
        return gameDao.getCurrentPlayerName()
    }
    override suspend fun setCurrentPlayerName(currentPlayerName: String) {
        return gameDao.setCurrentPlayerName(currentPlayerName)
    }

    override suspend fun getShowRestartButton(): Boolean {
        return gameDao.getShowRestartButton()
    }
    override suspend fun setShowRestartButton(showRestartButton: Boolean) {
        return gameDao.setShowRestartButton(showRestartButton)
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
    override suspend fun setCoordinates(startX: Float, startY: Float, endX: Float, endY: Float) {
        return gameDao.setCoordinates(startX, startY, endX, endY)
    }

    override suspend fun getShowPlayer1Name(): String {
        return gameDao.getShowPlayer1Name()
    }
    override suspend fun setShowPlayer1Name(showPlayer1Name: String) {
        return gameDao.setShowPlayer1Name(showPlayer1Name)
    }

    override suspend fun getShowPlayer2Name(): String {
        return gameDao.getShowPlayer2Name()
    }
    override suspend fun setShowPlayer2Name(showPlayer2Name: String) {
        return gameDao.setShowPlayer2Name(showPlayer2Name)
    }


}