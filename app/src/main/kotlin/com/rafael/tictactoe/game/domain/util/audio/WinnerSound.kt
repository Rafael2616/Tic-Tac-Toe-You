package com.rafael.tictactoe.game.domain.util

import android.content.Context
import android.media.MediaPlayer
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun WinnerSound(context: Context, vm: GameViewModel, isSoundEffectsOn: Boolean, isWinSoundPlaying: Boolean) {

    val winnerSound = MediaPlayer.create(context, R.raw.winner_victory_sound)

	if (isSoundEffectsOn && !isWinSoundPlaying) {
   	 winnerSound.start()
   	 vm.updateIsWinSoundPlaying(true)
    }
}