package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.R
import android.content.Context
import android.media.MediaPlayer
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun GotOldSound(context: Context, vm: GameViewModel, isSoundEffectsOn: Boolean, isGotOldSoundPlaying: Boolean) {

	val gotOldSound = MediaPlayer.create(context, R.raw.got_old_sound)
	
	if (isSoundEffectsOn && !isGotOldSoundPlaying) {
 	   gotOldSound.start()
        vm.updateIsGotOldSoundPlaying(true)
    }
}