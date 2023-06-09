package com.rafael.tictactoe.game.domain.util

import android.content.*
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.common.services.MusicService
import kotlinx.coroutines.runBlocking


fun BackgroundMusicLogic(
 repository: GameRepository,
 settingsRepo: SettingsRepository,
 context: Context
) = runBlocking {

  val isBackgroundMusicOn = settingsRepo.getIsBackgroundMusicOn()

    if (isBackgroundMusicOn) {
        val serviceIntent = Intent(context, MusicService::class.java)
        context.startService(serviceIntent)
        settingsRepo.setIsBackgroundMusicOn(false)
    } else {
        val serviceIntent = Intent(context, MusicService::class.java)
        context.stopService(serviceIntent)
        settingsRepo.setIsBackgroundMusicOn(true)
    }
}
