package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository


suspend fun ExpandButtonLogic(repository: GameRepository) {

	val expandButton = repository.getExpandButton()
	repository.setExpandButton(!expandButton)
}