package com.rafael.tictactoe.game.domain.util

import android.content.Context
import android.media.MediaPlayer
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository


suspend fun WinnerSound(context: Context, repository: GameRepository, settingsRepo: SettingsRepository) {

	val game = repository.getGameValues()
    val settings = settingsRepo.getAllSettingsValues()
    val isSoundEffectsOn = settings.isSoundEffectsOn
    val isWinSoundPlaying = game.isWinSoundPlaying
    val winnerSound = MediaPlayer.create(context, R.raw.winner_victory_sound)

if (isSoundEffectsOn && !isWinSoundPlaying) {
    winnerSound.start()
    game.isWinSoundPlaying = true
    repository.setGameValues(game)
    }
}