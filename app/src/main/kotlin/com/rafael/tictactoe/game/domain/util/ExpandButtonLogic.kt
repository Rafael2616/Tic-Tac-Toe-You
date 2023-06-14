package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository


suspend fun ExpandButtonLogic(repository: GameRepository) {

val game = repository.getGameValues()

/*game.expandButton = !game.expandButton
repository.setGameValues(game)
*/

	val expandButton = repository.getExpandButton()
	repository.setExpandButton(!expandButton)
}
