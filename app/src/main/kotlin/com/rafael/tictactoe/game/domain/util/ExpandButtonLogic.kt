package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*


fun ExpandButtonLogic(
repository: GameRepository
) = runBlocking {

val expandButton = repository.getExpandButton()
repository.setExpandButton(!expandButton)

}