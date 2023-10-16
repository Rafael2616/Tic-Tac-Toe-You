package com.rafael.tictactoe.game.domain.audio

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun GotOldSound(
    context: Context,
    vm: GameViewModel,
    settingsVM: SettingsViewModel,
    exoPlayer: ExoPlayer
) {
	val gotOldSoundFile = R.raw.got_old_sound
    val gotOldSound = MediaItem.fromUri(Uri.parse("android.resource://$context/$gotOldSoundFile"))
    val isSoundEffectsOn = settingsVM.isSoundEffectsOn.value
    val isGotOldSoundPlaying = vm.isGotOldSoundPlaying.value

	if (isSoundEffectsOn && !isGotOldSoundPlaying) {
		exoPlayer.addMediaItem(gotOldSound)
 	   exoPlayer.prepare()
        exoPlayer.setMediaItem(gotOldSound)
		exoPlayer.play()
        vm.updateIsGotOldSoundPlaying(true)
    }
}