package com.rafael.tictactoe.game.domain.util

import android.content.Context
import android.media.MediaPlayer
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository


suspend fun WinnerSound(repository: GameRepository, settingsRepo: SettingsRepository, context: Context) {

    val isSoundEffectsOn = settingsRepo.getIsSoundEffectsOn()
    val isWinSoundPlaying = repository.getIsWinSoundPlaying()
    val winnerSound = MediaPlayer.create(context, R.raw.winner_victory_sound)

if (isSoundEffectsOn && !isWinSoundPlaying) {
    winnerSound.start()
    repository.setIsWinSoundPlaying(true)
    }
}