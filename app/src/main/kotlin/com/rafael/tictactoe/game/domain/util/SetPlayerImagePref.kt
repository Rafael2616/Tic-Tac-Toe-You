package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository


suspend fun SetPlayerImagePref(repository: GameRepository) {

	val play = repository.getPlay()
	val isXFor1 = repository.getIsXFor1()

	if (!play){
		if (isXFor1){
			repository.setIsXImage(true)
	} else {
			repository.setIsXImage(false)
		}
    }
}