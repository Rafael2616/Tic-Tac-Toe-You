package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.R
import android.content.Context
import android.media.MediaPlayer
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository


suspend fun GotOldSound(context: Context, repository: GameRepository, settingsRepo: SettingsRepository) {

    val game = repository.getGameValues()
    val settings = settingsRepo.getAllSettingsValues()
	val gotOldSound = MediaPlayer.create(context, R.raw.got_old_sound)
	val isSoundEffectsOn = settings.isSoundEffectsOn
	val isGotOldSoundPlaying = game.isGotOldSoundPlaying
	
	if (isSoundEffectsOn && !isGotOldSoundPlaying){
 	   gotOldSound.start()
        game.isGotOldSoundPlaying = true
  	  repository.setGameValues(game)
    }
}