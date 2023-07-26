package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.R
import android.content.Context
import android.net.Uri
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.common.MediaItem
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun GotOldSound(context: Context, vm: GameViewModel, isSoundEffectsOn: Boolean, isGotOldSoundPlaying: Boolean) {

    val exoPlayer = ExoPlayer.Builder(context).build()
	val gotOldSoundFile = R.raw.got_old_sound
    val gotOldSound = MediaItem.fromUri(Uri.parse("android.resource://$context/$gotOldSoundFile"))

	if (isSoundEffectsOn && !isGotOldSoundPlaying) {
		exoPlayer.addMediaItem(gotOldSound)
 	   exoPlayer.prepare()
		exoPlayer.play()
        vm.updateIsGotOldSoundPlaying(true)
    } else {
		exoPlayer.stop()
		exoPlayer.release()
	}
}