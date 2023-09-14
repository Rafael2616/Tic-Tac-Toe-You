package com.rafael.tictactoe.game.domain.util

import android.content.Context
import android.net.Uri
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.common.MediaItem
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun CardGridSound(
	context: Context,
    viewModel: GameViewModel,
    settingsVM: SettingsViewModel
) {
    val isPlayer1 = viewModel.isPlayer1.value
    val isSoundEffectsOn = settingsVM.isSoundEffectsOn.value
<<<<<<< HEAD
    
=======

>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
	if (isSoundEffectsOn) {	
   	 val exoPlayer = ExoPlayer.Builder(context).build()
		val clickSoundFile = R.raw.click1_sound
		val clickSound2File = R.raw.click2_sound
 	   val clickSound1 = MediaItem.fromUri(Uri.parse("android.resource://$context/$clickSoundFile"))
 	   val clickSound2 = MediaItem.fromUri(Uri.parse("android.resource://$context/$clickSound2File"))

		if (isPlayer1) {
			exoPlayer.addMediaItem(clickSound1)
			exoPlayer.prepare()
			exoPlayer.play()
		} else {
			exoPlayer.addMediaItem(clickSound2)
			exoPlayer.prepare()
			exoPlayer.play()
		}
	}
}