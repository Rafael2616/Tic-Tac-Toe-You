package com.rafael.tictactoe.game.domain.audio

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun WinnerSound(
	context: Context,
	vm: GameViewModel,
    settingsVM: SettingsViewModel,
    exoPlayer: ExoPlayer
) {
	val winnerSoundFile = R.raw.winner_victory_sound
    val winnerSound = MediaItem.fromUri(Uri.parse("android.resource://$context/$winnerSoundFile"))
    val isSoundEffectsOn = settingsVM.isSoundEffectsOn.value
    val isWinSoundPlaying = vm.isWinSoundPlaying.value

	if (isSoundEffectsOn && !isWinSoundPlaying) {
   	 exoPlayer.addMediaItem(winnerSound)
		exoPlayer.prepare()
        exoPlayer.setMediaItem(winnerSound)
		exoPlayer.play()
   	 vm.updateIsWinSoundPlaying(true)
	}
}