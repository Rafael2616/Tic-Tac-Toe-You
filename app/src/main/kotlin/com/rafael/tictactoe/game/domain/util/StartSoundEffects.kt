package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*

fun StartSoundEffects(
repository: GameRepository
) = runBlocking {

val isSoundEffectsOn = repository.getIsSoundEffectsOn()
repository.setIsSoundEffectsOn(!isSoundEffectsOn)

}