package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.services.MediaService
import android.content.*
import androidx.activity.compose.*
import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*

/*
fun BackgroundMusicLogic(
repository: GameRepository
) = runBlocking {

     
val isBackgroundMusicOn = repository.getIsBackgroundMusicOn()

if (isBackgroundMusicOn) {
	startService(Intent(this, MediaService::class.java))
    repository.setIsBackgroundMusicOn(false)
} else {
	stopService(Intent(this, MediaService::class.java))
    repository.setIsBackgroundMusicOn(true)
	}
}
*/