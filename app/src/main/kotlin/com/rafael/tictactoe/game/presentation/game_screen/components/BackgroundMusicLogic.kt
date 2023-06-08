package com.rafael.tictactoe.game.domain.util

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.game.services.MediaService
import kotlinx.coroutines.runBlocking

@Composable
fun BackgroundMusicLogic(
 repository: GameRepository,
 settingsRepo: SettingsRepository,
 context: Context
) = runBlocking {

    val isBackgroundMusicOn = settingsRepo.getIsBackgroundMusicOn()

    if (isBackgroundMusicOn) {
        val serviceIntent = Intent(context, MediaService::class.java)
        context.startService(serviceIntent)
        settingsRepo.setIsBackgroundMusicOn(false)
    } else {
        val serviceIntent = Intent(context, MediaService::class.java)
        context.stopService(serviceIntent)
        settingsRepo.setIsBackgroundMusicOn(true)
    }
}
