package com.rafael.tictactoe.game.domain.util

import android.content.Context
import android.net.Uri
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.common.MediaItem
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun WinnerSound(
	context: Context,
	vm: GameViewModel,
	isSoundEffectsOn: Boolean,
	isWinSoundPlaying: Boolean
) {
    val exoPlayer = ExoPlayer.Builder(context).build()
	val winnerSoundFile = R.raw.winner_victory_sound
    val winnerSound = MediaItem.fromUri(Uri.parse("android.resource://$context/$winnerSoundFile"))

	if (isSoundEffectsOn && !isWinSoundPlaying) {
   	 exoPlayer.addMediaItem(winnerSound)
		exoPlayer.prepare()
		exoPlayer.play()
   	 vm.updateIsWinSoundPlaying(true)
	}
}