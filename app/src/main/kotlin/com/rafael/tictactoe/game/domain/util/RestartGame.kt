package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.repository.GameRepository

suspend fun RestartGame(repository: GameRepository) {

	val gameDefaults = GameEntity.defaultGameValues()
    val game = repository.getGameValues() ?: gameDefaults
    val cards = game.cards
    val isCardClicked = game.isCardClicked

   // game.isCardClicked.all { false }
   // game.cards.all { false }
	game.isScoreUp = false
    game.is1Win = false
    game.isWinSoundPlaying = false
    game.isGotOldSoundPlaying = false
    game.expandButton = false
    game.startX = 0f
    game.startY = 0f
    game.endX = 0f
	game.endY = 0f
    repository.setGameValues(game)
}